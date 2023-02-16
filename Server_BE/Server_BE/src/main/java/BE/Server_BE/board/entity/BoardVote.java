package BE.Server_BE.board.entity;

import BE.Server_BE.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class BoardVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column
    private int vote;

    public BoardVote(Member member, Board board, int vote){
        this.member = member;
        this.board = board;
        this.vote = vote;
    }

}
