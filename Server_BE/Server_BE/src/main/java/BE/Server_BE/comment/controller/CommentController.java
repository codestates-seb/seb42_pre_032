package BE.Server_BE.comment.controller;


import BE.Server_BE.MultiResponse;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.comment.mapper.CommentMapper;
import BE.Server_BE.comment.service.CommentService;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;
import BE.Server_BE.springsecurity.HelloUserDetailsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Validated
public class CommentController {
    private final CommentMapper commentMapper;
    private final CommentService commentService;
    private final MemberService memberService;
    private final AnswerService answerService;

    private final String url = "http://localhost:8080/comments/";

    public CommentController(CommentMapper commentMapper, CommentService commentService, MemberService memberService, AnswerService answerService) {
        this.commentMapper = commentMapper;
        this.commentService = commentService;
        this.memberService = memberService;
        this.answerService = answerService;
    }

    @PostMapping("/{answer-id}")
    public ResponseEntity postComment (@PathVariable("answer-id") @Positive long answerId,
                                       @Valid @RequestBody CommentDto.Post post,
                                       Principal principal) {

        Comment comment = commentMapper.commentDtoPostToComment(post);
        Member member = memberService.findMemberByEmail(principal.getName());
        comment.setMember(member);
        comment.setAnswer(answerService.findAnswer(answerId));
        Comment createdComment = commentService.createComment(comment);

        CommentDto.Response response = commentMapper.commentToCommentDtoResponse(createdComment);
        response.setUrl(url+response.getCommentId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment (@PathVariable("comment-id") @Positive long commentId,
                                        @RequestBody @Valid CommentDto.Patch patch) {
        Comment comment = commentMapper.commentDtoPatchToComment(patch);
        comment.setCommentId(commentId);
        Comment updatedComment = commentService.updateComment(comment);
        CommentDto.Response response = commentMapper.commentToCommentDtoResponse(updatedComment);
        response.setUrl(url+response.getCommentId());
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/{comment-id}")
    public ResponseEntity getComment (@PathVariable("comment-id") @Positive long commentId){
        Comment findComment = commentService.getComment(commentId);
        CommentDto.Response response = commentMapper.commentToCommentDtoResponse(findComment);
        response.setUrl(url+commentId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getComments(@Positive @RequestParam(required = false, defaultValue = "1") int page)throws Exception{
        Page<Comment> commentPage = commentService.getComments(page);
        PageInfo pageInfo = new PageInfo(commentPage.getNumber(), commentPage.getSize(),
                commentPage.getTotalElements(),commentPage.getTotalPages());
        List<Comment> comments = commentPage.getContent();
        List<CommentDto.Response> responses = commentMapper.commentsToCommentDtoResponse(comments);
        responses.stream().forEach(s -> s.setUrl(url+s.getCommentId()));

        return new ResponseEntity<>(
                new MultiResponse<>(responses, pageInfo),HttpStatus.OK);
    }
    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@Positive @PathVariable("comment-id")long commentId) throws Exception {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    private Comment buildComment(long answerId, String email,String body) {
        Comment comment = Comment.builder()
                .body(body)
                .build();
        comment.setAnswer(answerService.getAnswer(answerId));
        comment.setMember(memberService.findMemberByEmail(email));
        return comment;
    }
}
