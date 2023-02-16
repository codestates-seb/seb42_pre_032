package BE.Server_BE.answer.repository;

import BE.Server_BE.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository  extends JpaRepository<Answer, Long> {
}
