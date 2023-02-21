package BE.Server_BE.answer.controller;


import BE.Server_BE.MultiResponse;
import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.answer.service.AnswerVoteService;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.comment.mapper.CommentMapper;
import BE.Server_BE.comment.service.CommentService;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;
import BE.Server_BE.springsecurity.HelloUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/boards/{board-id}/answers")
@Validated
@Slf4j
public class AnswerController{
    private final String url = "http://localhost:8080/boards/{board-id}/answers/";

    private final AnswerVoteService answerVoteService;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final BoardService boardService;
    private final MemberService memberService;

    public AnswerController(AnswerVoteService answerVoteService,
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

    @PostMapping("")
    public ResponseEntity postAnswer(@PathVariable("board-id") @Positive long boardId,
                                     @Valid @RequestBody AnswerDto.Post requestBody,
                                     Principal principal) {
        Answer answer = buildAnswer(
                boardId,
                principal.getName(),
                requestBody.getTitle(),
                requestBody.getBody()
        );
        answerService.createAnswer(answer);
        return ResponseEntity.status(HttpStatus.CREATED).build();

//        Answer answer = answerMapper.answerPostToAnswer(requestBody);
//        Member member = memberService.findMemberByEmail(principal.getName());
//        answer.setMember(member);
//        Answer createdAnswer = answerService.createAnswer(answer);
//
//        AnswerDto.Response response = answerMapper.answerToAnswerResponse(createdAnswer);
//        response.setUrl(url+response.getAnswerId());
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    private Answer buildAnswer(long boardId, String email,String title, String body) {
        Answer answer = Answer.builder()
                .title(title)
                .body(body)
                .build();
        answer.setBoard(boardService.findBoard(boardId));
        answer.setMember(memberService.findMemberByEmail(email));

        return answer;
    }
    @PatchMapping("{answer-id}")
    public ResponseEntity patchAnswer (@PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody AnswerDto.Patch requestBody){

        Answer answer = answerService.updateAnswer(answerMapper.answerPatchToAnswer(requestBody));

        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping("{answer-id}")
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

