package BE.Server_BE.vote.entity;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Column
    private long memberId;

    @Column
    private long answerId;

    @Column
    private long boardId;

    @Column
    private int vote;

    public void setAnswerVote(long answerId, long memberId, int vote){
        this.answerId = answerId;
        this.memberId = memberId;
        this.vote = vote;
    }

    public void setBoardVote(long boardId, long memberId, int vote){
        this.boardId = boardId;
        this.memberId = memberId;
        this.vote = vote;
    }

}
