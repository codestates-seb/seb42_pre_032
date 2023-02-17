package BE.Server_BE.comment.repository;

import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Comment findById(long commentId);
}
