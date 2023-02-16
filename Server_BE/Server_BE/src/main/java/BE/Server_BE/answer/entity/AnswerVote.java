package BE.Server_BE.answer.entity;

import BE.Server_BE.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @Column
    private int vote;

    public AnswerVote(Member member, Answer answer, int vote){
        this.member = member;
        this.answer = answer;
        this.vote = vote;
    }

}
