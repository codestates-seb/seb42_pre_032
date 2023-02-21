package BE.Server_BE.answer.service;

import BE.Server_BE.advice.BusinessLogicException;
import BE.Server_BE.advice.ExceptionCode;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.repository.AnswerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer){

        Answer savedAnswer = answerRepository.save(answer);

        return savedAnswer;
    }

    public Answer findAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                 new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY));
        return findAnswer;
    }

    @Transactional(readOnly = true)
    public Page<Answer> getAnswers(int page) {

        return answerRepository.findAll(PageRequest.of(page-1, 15,
                Sort.by("answerId").descending()));
    }

    public Answer updateAnswer(Answer answer) {
        Answer target = getAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getTitle())
                .ifPresent(title -> target.setTitle(title));
        Optional.ofNullable(answer.getBody())
                .ifPresent(body -> target.setBody(body));

        return answerRepository.save(target);
    }

    public void deleteAnswer(long answerId){
        Answer findMember = getAnswer(answerId);

        answerRepository.delete(findMember);
    }
}
