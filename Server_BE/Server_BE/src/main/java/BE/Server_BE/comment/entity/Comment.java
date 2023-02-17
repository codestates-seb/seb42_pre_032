package BE.Server_BE.comment.entity;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.audit.Auditable;
import BE.Server_BE.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment extends Auditable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long commentId;

    @Column
    String body;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    Member member;

    public void addMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name="ANSWER_ID")
    Answer answer;

    public void addAnswer(Answer answer) {
        this.answer = answer;
    }


}
