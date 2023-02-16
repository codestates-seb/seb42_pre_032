package BE.Server_BE.member.service;

import BE.Server_BE.advice.BusinessLogicException;
import BE.Server_BE.advice.ExceptionCode;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    public Member createMember(Member member) throws Exception {
        verifyEmail(member);
        // 등록된 이메일이 있을 경우, 예외 처리.
        return memberRepository.save(member);
    }
    // 중복 이메일이면 이메일 중복 예외 처리하기
    public Member updateMember(Member member) throws Exception{
        Member findMember = loadMember(member.getMemberId());
        // 입력된 id로 불러올 회원정보가 없다면 예외처리
        verifyEmail(member);
        // 입력된 회원정보에 이메일이 중복이라면 이메일 중복 예외 처리.
        Optional.ofNullable(member.getNickName())
                        .ifPresent(nickname -> findMember.setNickName(nickname));
        Optional.ofNullable(member.getPassword())
                        .ifPresent(password -> findMember.setPassword(password));
        Optional.ofNullable(member.getAbout_Me())
                        .ifPresent(aboutMe->findMember.setAbout_Me(aboutMe));
        return memberRepository.save(findMember);
    }
    // 회원 아이디로 조회되는게 없으면 회원이 없다고 예외처리하기
    public Member getMember(long memberId) {
        return loadMember(memberId);
    }

    public Page<Member> getMembers(int page, int size) throws Exception{
        if (memberRepository.findAll() == null) {
            throw new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY);
        }
        return memberRepository.findAll(PageRequest.of(
                page, size
        ));
    }
    public void deleteMember(long memberId) throws Exception{
        Member deleteMember = loadMember(memberId);
        memberRepository.delete(deleteMember);
    }
    public void verifyEmail(Member member) throws Exception {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new BusinessLogicException(ExceptionCode.EMAIL_IS_DUPLICATED);
        }
    }
    public Member loadMember(long memberId) {
        Member findMember = memberRepository.findById(memberId);
        if (findMember == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return findMember;
    }
}
