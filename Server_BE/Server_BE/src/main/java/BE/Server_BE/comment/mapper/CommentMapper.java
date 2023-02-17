package BE.Server_BE.comment.mapper;

import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.member.dto.MemberDto;
import BE.Server_BE.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDtoPostToComment(CommentDto.Post post);
    Comment commentDtoPatchToComment(CommentDto.Patch patch);
    CommentDto.Response commentToCommentDtoResponse(Comment comment);
    List<CommentDto.Response> commentsToCommentDtoResponse (List<Comment> comments);


}
