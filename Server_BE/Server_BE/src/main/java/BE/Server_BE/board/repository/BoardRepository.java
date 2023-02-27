package BE.Server_BE.board.repository;

import BE.Server_BE.board.entity.Board;
import BE.Server_BE.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "SELECT * FROM board WHERE TITLE LIKE CONCAT('%', :q ,'%')", nativeQuery = true)
    public Page<Board> findAllByTitle(@Param("q")String q, Pageable pageable);
}
