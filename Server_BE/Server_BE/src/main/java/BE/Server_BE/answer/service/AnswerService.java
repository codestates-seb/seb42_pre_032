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

    @Transactional(readOnly = true)
    public Answer findAnswer(long answerId) {
        Optional<Answer> optionalQuestion =
                answerRepository.findById(answerId);
        Answer findAnswer =
                optionalQuestion.orElseThrow(() ->
                 new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY));
        return findAnswer;
    }

    @Transactional(readOnly = true)
    public Page<Answer> findAllAnswers(int page, int size) {

        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }

    public Answer updateAnswer(Answer answer) {
        Answer target = findAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getTitle())
                .ifPresent(title -> target.setTitle(title));
        Optional.ofNullable(answer.getBody())
                .ifPresent(body -> target.setBody(body));

        return answerRepository.save(target);
    }

    public void deleteAnswer(long answerId){
        Answer findMember = findAnswer(answerId);

        answerRepository.delete(findMember);
    }
}
