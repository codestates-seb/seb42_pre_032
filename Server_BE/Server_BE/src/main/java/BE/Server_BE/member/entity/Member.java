package BE.Server_BE.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long memberId;
    @Column
    String nickName;
    @Column(updatable = false, unique = true)
    @Email
    String email;
    @Column
    String password;
    @Column
    String about_Me;
}
