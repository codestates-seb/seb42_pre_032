package BE.Server_BE.comment.controller;


import BE.Server_BE.MultiResponse;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.comment.mapper.CommentMapper;
import BE.Server_BE.comment.service.CommentService;
import BE.Server_BE.member.response.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Validated
public class CommentController {
    CommentMapper mapper;
    CommentService commentService;

    private final String url = "http://localhost:8080/comments/";

    public CommentController(CommentMapper mapper, CommentService commentService) {
        this.mapper = mapper;
        this.commentService = commentService;
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment (@PathVariable("comment-id") @Positive long commentId,
                                        @RequestBody @Valid CommentDto.Patch patch) {
        Comment comment = mapper.commentDtoPatchToComment(patch);
        comment.setCommentId(commentId);
        Comment updatedComment = commentService.updateComment(comment);
        CommentDto.Response response = mapper.commentToCommentDtoResponse(updatedComment);
        response.setUrl(url+response.getCommentId());
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/{comment-id}")
    public ResponseEntity getComment (@PathVariable("comment-id") @Positive long commentId){
        Comment findComment = commentService.getComment(commentId);
        CommentDto.Response response = mapper.commentToCommentDtoResponse(findComment);
        response.setUrl(url+commentId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getComments(@Positive @RequestParam(required = false, defaultValue = "1") int page)throws Exception{
        Page<Comment> commentPage = commentService.getComments(page);
        PageInfo pageInfo = new PageInfo(commentPage.getNumber(), commentPage.getSize(),
                commentPage.getTotalElements(),commentPage.getTotalPages());
        List<Comment> comments = commentPage.getContent();
        List<CommentDto.Response> responses = mapper.commentsToCommentDtoResponse(comments);
        responses.stream().forEach(s -> s.setUrl(url+s.getCommentId()));

        return new ResponseEntity<>(
                new MultiResponse<>(responses, pageInfo),HttpStatus.OK);
    }
    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@Positive @PathVariable("comment-id")long commentId) throws Exception {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
