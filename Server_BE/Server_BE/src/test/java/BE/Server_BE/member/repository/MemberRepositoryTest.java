package BE.Server_BE.member.repository;

import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveMemberTest() {
        Member member = new Member();
        member.setNickName("Paul");
        member.setEmail("Paul@gmail.com");
        member.setPassword("Paul");
        member.setAbout_Me("Paul");

        Member savedMember = memberRepository.save(member);

        assertNotNull(savedMember);
        assertTrue(member.getNickName().equals(savedMember.getNickName()));
        assertTrue(member.getEmail().equals(savedMember.getEmail()));
        assertTrue(member.getPassword().equals(savedMember.getPassword()));
        assertTrue(member.getAbout_Me().equals(savedMember.getAbout_Me()));
    }


}
