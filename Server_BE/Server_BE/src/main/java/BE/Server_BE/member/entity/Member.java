package BE.Server_BE.member.entity;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.audit.Auditable;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends Auditable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long memberId;

    @Column
    private String nickName;

    @Column(updatable = false, unique = true)
    @Email
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column
    private String about_Me;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    // 테스트 하기 위해 추가
    public Member(long memberId, String nickName, String email, String password, String about_Me) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.about_Me = about_Me;
    }

    //
    public void addBoard(Board board){
        boards.add(board);
    }
    public void addAnswer(Answer answer){
        answers.add(answer);
    }
    public void addComment(Comment comment){
        comments.add(comment);
    }
}
