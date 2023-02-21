package BE.Server_BE.comment.service;

import BE.Server_BE.advice.BusinessLogicException;
import BE.Server_BE.advice.ExceptionCode;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.comment.repository.CommentRepository;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    CommentRepository commentRepository;
    MemberService memberService;
    AnswerService answerService;

    public CommentService(CommentRepository commentRepository, MemberService memberService, AnswerService answerService) {
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.answerService = answerService;
    }

    public Comment createComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }
    public Comment updateComment(Comment comment) {
        Comment findComment = loadComment(comment.getCommentId());

        Optional.ofNullable(comment.getBody())
                .ifPresent(commentBody->findComment.setBody(commentBody));
        return commentRepository.save(findComment);
    }
    public Comment getComment(long commentId) {
        return loadComment(commentId);
    }
    public Page<Comment> getComments(int page, int size) throws Exception {
        if (commentRepository.findAll() == null) {
            throw new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY);
        }
        return commentRepository.findAll(PageRequest.of(
                page, size
        ));
    }
    public void deleteComment(long commentId) throws Exception {
        Comment deletedComment = loadComment(commentId);
        commentRepository.delete(deletedComment);
    }
    public Comment loadComment(long commentId) {
        Comment loadedComment = commentRepository.findById(commentId);
        if (loadedComment == null) {
            throw new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY);
        }
        return loadedComment;
    }

}
