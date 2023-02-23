package BE.Server_BE.answer.mapper;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostToAnswer (AnswerDto.Post requestBody);
    Answer answerPatchToAnswer (AnswerDto.Patch requestBody);


    default AnswerDto.Response answerToAnswerResponse(Answer answer){
        List<Comment> commentList = answer.getComments();
        final String url = "http://localhost:8080/answers/";
        AnswerDto.Response response =AnswerDto.Response
                .builder()
                .answerId(answer.getAnswerId())
                .title(answer.getTitle())
                .body(answer.getBody())
                .boardId(answer.getBoard().getBoardId())
                .memberId(answer.getMember().getMemberId())
                .like(answer.getVote())
                .url(url+answer.getAnswerId())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .comments(commentsToCommentResponses(commentList))
                .build();
        return response;
    }
    default List<AnswerDto.Response> answersToAnswerResponses (List<Answer> answers) {

        final String url = "http://localhost:8080/answers/";
        return answers
                .stream()
                .map(answer -> {
                    List<CommentDto.Response> commentResponses
                            = commentsToCommentResponses(answer.getComments());
                    return AnswerDto.Response
                            .builder()
                            .boardId(answer.getBoard().getBoardId())
                            .answerId(answer.getAnswerId())
                            .title(answer.getTitle())
                            .body(answer.getBody())
                            .memberId(answer.getMember().getMemberId())
                            .like(answer.getVote())
                            .url(url+answer.getAnswerId())
                            .createdAt(answer.getCreatedAt())
                            .modifiedAt(answer.getModifiedAt())
                            .comments(commentResponses)
                            .build();
                }).collect(Collectors.toList());
    }
    default List<CommentDto.Response> commentsToCommentResponses (List<Comment> comments) {
        final String url = "http://localhost:8080/comments";
        return comments
                .stream()
                .map(comment -> CommentDto.Response
                        .builder()
                        .commentId(comment.getCommentId())
                        .body(comment.getBody())
                        .memberId(comment.getMember().getMemberId())
                        .answerId(comment.getAnswer().getAnswerId())
                        .createdAt(comment.getCreatedAt())
                        .modifiedAt(comment.getModifiedAt())
                        .url(url+comment.getCommentId())
                        .build()).collect(Collectors.toList());
    }
}
