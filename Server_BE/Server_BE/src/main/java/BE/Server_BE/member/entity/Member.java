package BE.Server_BE.member.entity;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.audit.Auditable;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends Auditable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long memberId;
    @Column
    String nickName;
    @Column(updatable = false, unique = true)
    @Email
    String email;
    @Column
    String password;
    @Column
    String about_Me;

    @OneToMany(mappedBy = "member")
    List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Comment> comments = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

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
