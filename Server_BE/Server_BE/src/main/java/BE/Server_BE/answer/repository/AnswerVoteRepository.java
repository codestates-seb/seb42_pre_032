package BE.Server_BE.answer.repository;

import BE.Server_BE.answer.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    @Query(value = "SELECT SUM(VOTE) FROM ANSWER_VOTE WHERE ANSWER_ID = :answerId", nativeQuery = true)
    Long getVoteCount(long answerId);
}
