package BE.Server_BE.member.service;

import BE.Server_BE.advice.BusinessLogicException;
import BE.Server_BE.advice.ExceptionCode;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.repository.MemberRepository;
import BE.Server_BE.springsecurity.HelloAuthorityUtils;
import BE.Server_BE.springsecurity.MemberRegistrationApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final HelloAuthorityUtils authorityUtils;
    private final ApplicationEventPublisher publisher;

    public MemberService(MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder,
                         HelloAuthorityUtils authorityUtils,
                         ApplicationEventPublisher publisher) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
        this.publisher = publisher;
    }

    public Member createMember(Member member) throws Exception {
        verifyExistedEmail(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member createdMember = memberRepository.save(member);
        publisher.publishEvent(new MemberRegistrationApplicationEvent(this, createdMember));
        return createdMember;
    }
    // 중복 이메일이면 이메일 중복 예외 처리하기
    public Member updateMember(Member member) throws Exception{
        Member findMember = loadMember(member.getMemberId());
        // 입력된 id로 불러올 회원정보가 없다면 예외처리
        verifyExistedEmail(member.getEmail());
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

    public Page<Member> getMembers(int page) throws Exception {
        if (memberRepository.findAll() == null) {
            throw new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY);
        }
        return memberRepository.findAll(PageRequest.of(
                page-1, 15
        ));
    }
    public void deleteMember(long memberId) throws Exception{
        Member deleteMember = loadMember(memberId);
        memberRepository.delete(deleteMember);
    }
    // 존재하는 이메일인지 확인
    public void verifyExistedEmail(String email) throws Exception {
        Optional<Member> foundEmail = memberRepository.findByEmail(email);
        if (foundEmail.isPresent())
            throw new BusinessLogicException(ExceptionCode.EMAIL_IS_DUPLICATED);
    }
    // 존재하는 회원인지 확인
    public Member loadMember(long memberId) {
        Member findMember = memberRepository.findById(memberId);
        if (findMember == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return findMember;
    }
    // 이메일을 통해 회원 확인
    public Member findMemberByEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        Member findMember = optionalMember.orElseThrow(()
                ->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }
}
