package BE.Server_BE.member.service;

import BE.Server_BE.advice.BusinessLogicException;
import BE.Server_BE.advice.ExceptionCode;
import BE.Server_BE.member.dto.MemberDto;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.annotation.Repeatable;
import java.util.Optional;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) throws Exception {
        verifyEmail(member);
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) throws Exception{
        Member findMember = loadMember(member.getMemberId());

        Optional.ofNullable(member.getNickName())
                        .ifPresent(nickname -> findMember.setNickName(nickname));
        Optional.ofNullable(member.getPassword())
                        .ifPresent(password -> findMember.setPassword(password));
        Optional.ofNullable(member.getAbout_Me())
                        .ifPresent(aboutMe->findMember.setAbout_Me(aboutMe));

        return memberRepository.save(findMember);
    }
    public Member getMember(long memberId) {
        return loadMember(memberId);
    }
    public Page<Member> getMembers(int page, int size) {
        if (memberRepository.findAll() == null) {
            throw new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY);
        }else
        return memberRepository.findAll(PageRequest.of(
                page, size
        ));
    }
    public void deleteMember(long memberId) throws Exception{
        Member deleteMember = loadMember(memberId);
        memberRepository.delete(deleteMember);
    }

    public void verifyEmail(Member member) throws Exception{
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXIST);
        }
    }
    
    public Member loadMember(long memberId) {
        Member findMember = memberRepository.findById(memberId);
        if (findMember == null) {
            throw new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY);
        }
        return findMember;
    }
}
