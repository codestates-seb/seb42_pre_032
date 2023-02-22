package BE.Server_BE.answer.entity;

import BE.Server_BE.audit.Auditable;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.vote.entity.Vote;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 50)
    private String title;

    @Column
    private String body;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column
    private Long vote;

    @OneToMany(mappedBy = "answer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Answer(long answerId, String title, String body, Member member, Board board) {
        this.answerId = answerId;
        this.title = title;
        this.body = body;
    }
    public void setMember(Member member) {
        this.member = member;
        if (!member.getAnswers().contains(this)) {
            member.getAnswers().add(this);
        }
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public void addComment(Comment comment) {
        comments.add(comment);
        if (comment.getAnswer() != this) {
            comment.setAnswer(this);
        }
    }

}