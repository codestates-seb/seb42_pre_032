package BE.Server_BE.answer.service;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.entity.AnswerVote;
import BE.Server_BE.answer.repository.AnswerRepository;
import BE.Server_BE.answer.repository.AnswerVoteRepository;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class AnswerVoteService {
    private final AnswerVoteRepository answerVoteRepository;
    private final AnswerService answerService;
    private final MemberService memberService;

    public AnswerVoteService(AnswerVoteRepository answerVoteRepository,
                             AnswerService answerService,
                             MemberService memberService){
        this.answerVoteRepository = answerVoteRepository;
        this.answerService = answerService;
        this.memberService = memberService;
    }

    public void createLike(long answerId, long memberId){
        Answer answer = answerService.getAnswer(answerId);
        Member member = memberService.loadMember(memberId);
        AnswerVote vote = new AnswerVote(member, answer, 1);

        answerVoteRepository.save(vote);
    }

    public void createDislike(long answerId, long memberId){
        Answer answer = answerService.getAnswer(answerId);
        Member member = memberService.loadMember(memberId);
        AnswerVote vote = new AnswerVote(member, answer, -1);

        answerVoteRepository.save(vote);
    }

    public long getVote(long answerId){
        return answerVoteRepository.getVoteCount(answerId);
    }
}
