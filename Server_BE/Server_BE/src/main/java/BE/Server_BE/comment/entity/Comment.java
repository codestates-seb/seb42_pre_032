package BE.Server_BE.comment.entity;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.audit.Auditable;
import BE.Server_BE.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Auditable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long commentId;

    @Column
    @NotBlank(message = "내용은 공백이 아니어야 합니다")
    private String body;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="ANSWER_ID")
    private Answer answer;

    @Builder
    public Comment(long commentId, String body, Member member, Answer answer) {
        this.commentId = commentId;
        this.body = body;
    }
    public void setMember(Member member) {
        this.member = member;
        if (!member.getComments().contains(this)) {
            member.getComments().add(this);
        }
    }
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}
