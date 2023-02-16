package BE.Server_BE.member.repository;

import BE.Server_BE.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmail(String email);
    public Member findById(long memberId);

}
