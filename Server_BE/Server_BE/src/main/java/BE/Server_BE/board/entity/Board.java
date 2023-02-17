package BE.Server_BE.board.entity;

import BE.Server_BE.audit.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



@Getter
@Setter
@Entity
public class Board extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;


    private Long memberId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;


//    @OneToMany(mappedBy = "BOARD")
//    private List<String> answers = new ArrayList<>();


}
