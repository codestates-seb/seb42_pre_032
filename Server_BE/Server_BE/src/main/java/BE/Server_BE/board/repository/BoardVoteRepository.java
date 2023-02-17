package BE.Server_BE.board.repository;

import BE.Server_BE.board.entity.BoardVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardVoteRepository extends JpaRepository<BoardVote, Long> {
    @Query(value = "SELECT SUM(VOTE) FROM BOARD_VOTE WHERE BOARD_ID = :boardId", nativeQuery = true)
    Long getVoteCount(long boardId);
}