package BE.Server_BE.answer.controller;


import BE.Server_BE.MultiResponse;
import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.entity.AnswerVote;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.answer.service.AnswerVoteService;
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
import java.util.List;

@RestController
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController{

    private final AnswerVoteService answerVoteService;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;

    public AnswerController(AnswerService answerService,
                            AnswerMapper answerMapper,
                            MemberService memberService,
                            AnswerVoteService answerVoteService){
        this.answerMapper = answerMapper;
        this.answerService = answerService;
        this.memberService = memberService;
        this.answerVoteService = answerVoteService;
    }

//    @PostMapping
//    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post requestBody) {
//
//    }


    //member정보와 board정보 받아오지 않음
    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody) {

        Answer answer = answerMapper.answerPostToAnswer(requestBody);

        answerService.createAnswer(answer);
        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(
            @PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page) {
        Page<Answer> answerPage = answerService.findAllAnswers(page - 1, 15);
        PageInfo pageInfo = new PageInfo(answerPage.getNumber(), answerPage.getSize(),
                answerPage.getTotalElements(),answerPage.getTotalPages());
        List<Answer> answers = answerPage.getContent();
        List<AnswerDto.Response> responses = answerMapper.answersToAnswerResponses(answers);

        return new ResponseEntity<>(
                new MultiResponse<>(responses, pageInfo), HttpStatus.OK
        );
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer (@PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody AnswerDto.Patch requestBody){

        Answer answer = answerService.updateAnswer(answerMapper.answerPatchToAnswer(requestBody));

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer (@PathVariable("answer-id") @Positive long answerId){
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{answer-id}/like")
    public ResponseEntity postLike (@PathVariable("answer-id") @Positive long answerId){
        answerVoteService.createLike(answerId, 1);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/{answer-id}/dislike")
    public ResponseEntity postDislike (@PathVariable("answer-id") @Positive long answerId){
        answerVoteService.createDislike(answerId, 1);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{answer-id}/vote")
    public ResponseEntity getVote (@PathVariable("answer-id") @Positive long answerId){

        return new ResponseEntity<>(answerVoteService.getVote(answerId),HttpStatus.OK);
    }

}

