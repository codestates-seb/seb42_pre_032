package BE.Server_BE.answer.controller;


import BE.Server_BE.MultiResponse;
import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.vote.service.VoteService;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController{
    private final String url = "http://localhost:8080/answers/";

    private final VoteService answerVoteService;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final BoardService boardService;
    private final MemberService memberService;

    public AnswerController(VoteService answerVoteService,
                            AnswerService answerService,
                            AnswerMapper answerMapper,
                            MemberService memberService,
                            BoardService boardService) {
        this.answerVoteService = answerVoteService;
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.memberService = memberService;
        this.boardService = boardService;
    }
    
    @PostMapping("/{board-id}")
    public ResponseEntity postAnswer(@PathVariable("board-id") @Positive long boardId,
                                     @Valid @RequestBody AnswerDto.Post requestBody,
                                     Principal principal) {

        Member member = memberService.findMemberByEmail(principal.getName());
        Answer answer = answerMapper.answerPostToAnswer(requestBody);
        answer.setMember(member);
        answer.setVote(0L);
        answer.setBoard(boardService.findVerifiedBoard(boardId));
        Answer createdAnswer = answerService.createAnswer(answer);

        AnswerDto.Response response = answerMapper.answerToAnswerResponse(createdAnswer);
        response.setUrl(url+response.getAnswerId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer (@PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody AnswerDto.Patch requestBody){

        Answer answer = answerService.updateAnswer(answerMapper.answerPatchToAnswer(requestBody));
        answer.setAnswerId(answerId);

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(
            @PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam(required = false, defaultValue = "1") int page) {
        Page<Answer> answerPage = answerService.getAnswers(page);
        PageInfo pageInfo = new PageInfo(answerPage.getNumber(), answerPage.getSize(),
                answerPage.getTotalElements(),answerPage.getTotalPages());
        List<Answer> answers = answerPage.getContent();
        List<AnswerDto.Response> responses = answerMapper.answersToAnswerResponses(answers);

        return new ResponseEntity<>(
                new MultiResponse<>(responses, pageInfo), HttpStatus.OK
        );
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer (@PathVariable("answer-id") @Positive long answerId){
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{answer-id}/like")
    public ResponseEntity postLike (@PathVariable("answer-id") @Positive long answerId,
                                    Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        answerVoteService.createAnswerLike(answerId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/{answer-id}/dislike")
    public ResponseEntity postDislike (@PathVariable("answer-id") @Positive long answerId,
                                       Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        answerVoteService.createAnswerDislike(answerId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{answer-id}/like")
    public ResponseEntity deleteLike (@PathVariable("answer-id") @Positive long answerId,
                                    Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        answerVoteService.deleteAnswerVote(answerId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{answer-id}/dislike")
    public ResponseEntity deleteDislike (@PathVariable("answer-id") @Positive long answerId,
                                       Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        answerVoteService.deleteAnswerVote(answerId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

