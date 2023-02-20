package BE.Server_BE.answer.controller;


import BE.Server_BE.MultiResponse;
import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.answer.service.AnswerVoteService;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.comment.mapper.CommentMapper;
import BE.Server_BE.comment.service.CommentService;
import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;
import BE.Server_BE.springsecurity.HelloUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final String url = "http://localhost:8080/comments/";

    private final AnswerVoteService answerVoteService;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;
    private final CommentMapper commentMapper;
    private final CommentService commentService;

    public AnswerController(AnswerVoteService answerVoteService,
                            AnswerService answerService,
                            AnswerMapper answerMapper,
                            MemberService memberService,
                            CommentMapper commentMapper,
                            CommentService commentService) {
        this.answerVoteService = answerVoteService;
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.memberService = memberService;
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @PostMapping("/{answer-id}")
    public ResponseEntity postComment (@PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody CommentDto.Post post,
                                       @AuthenticationPrincipal HelloUserDetailsService.HelloUserDetails userDetails) throws Exception{
        Comment comment = commentMapper.commentDtoPostToComment(post);
        comment.setMember(memberService.loadMember(userDetails.getMemberId()));
        comment.setAnswer(answerService.getAnswer(answerId));

        Comment createdComment = commentService.createComment(comment);
        CommentDto.Response response = commentMapper.commentToCommentDtoResponse(createdComment);
        response.setUrl(url+response.getCommentId());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(
            @PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.getAnswer(answerId);

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page) {
        Page<Answer> answerPage = answerService.getAnswers(page);
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

