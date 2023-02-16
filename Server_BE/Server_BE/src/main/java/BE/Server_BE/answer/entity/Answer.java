package BE.Server_BE.answer.entity;

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
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "WRITER")
    private Member member;

    @Column
    private String body;

//    @OneToOne
//    @JoinColumn(name = "BOARD_ID")
//    private Board board;

//    @OneToMany(mappedBy = "comment")
//    private List<Comment> comments = new ArrayList<>();

//    @Column
//    private Long like;


}