package BE.Server_BE.vote.repository;

import BE.Server_BE.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query(value = "SELECT SUM(VOTE) FROM VOTE WHERE ANSWER_ID = :answerId", nativeQuery = true)
    Long getAnswerVoteCount(long answerId);

    @Query(value = "SELECT * FROM VOTE WHERE ANSWER_ID = :answerId AND MEMBER_ID = :memberId", nativeQuery = true)
    Optional<Vote> getAnswerVote(long answerId, long memberId);

    @Query(value = "SELECT SUM(VOTE) FROM VOTE WHERE BOARD_ID = :boardId", nativeQuery = true)
    Long getBoardVoteCount(long boardId);

    @Query(value = "SELECT * FROM VOTE WHERE BOARD_ID = :boardId AND MEMBER_ID = :memberId", nativeQuery = true)
    Optional<Vote> getBoardVote(long boardId, long memberId);
}
