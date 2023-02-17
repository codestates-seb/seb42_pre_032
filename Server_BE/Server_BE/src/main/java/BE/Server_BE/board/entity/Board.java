package BE.Server_BE.board.entity;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.audit.Auditable;
import BE.Server_BE.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Board extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @OneToMany(mappedBy = "board")
    private List<Answer> answers = new ArrayList<>();


}
