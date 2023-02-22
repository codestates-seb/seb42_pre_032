package BE.Server_BE.comment.mapper;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.member.dto.MemberDto;
import BE.Server_BE.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDtoPostToComment(CommentDto.Post post);
    Comment commentDtoPatchToComment(CommentDto.Patch patch);

    default CommentDto.Response commentToCommentDtoResponse(Comment comment){
        CommentDto.Response response =CommentDto.Response
                .builder()
                .commentId(comment.getCommentId())
                .body(comment.getBody())
                .answerId(comment.getAnswer().getAnswerId())
                .memberId(comment.getMember().getMemberId())
                .build();
        return response;
    }

    default List<CommentDto.Response> commentsToCommentDtoResponse(List<Comment> comments){
        return comments
                .stream()
                .map(comment -> CommentDto.Response
                        .builder()
                        .memberId(comment.getMember().getMemberId())
                        .commentId(comment.getCommentId())
                        .body(comment.getBody())
                        .answerId(comment.getAnswer().getAnswerId())
                        .build())
                .collect(Collectors.toList());
    }

}
