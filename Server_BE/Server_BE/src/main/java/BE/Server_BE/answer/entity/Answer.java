package BE.Server_BE.answer.entity;

import BE.Server_BE.audit.Auditable;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.member.entity.Member;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column
    private String body;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

}