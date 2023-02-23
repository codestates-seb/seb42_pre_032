package BE.Server_BE.board.mapper;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;

import BE.Server_BE.comment.dto.CommentDto;

import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;

import BE.Server_BE.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    Board boardPostDtoToBoard(BoardDto.Post requestBody);
    Board boardPatchDtoToBoard(BoardDto.Patch requestBody);

    default List<BoardDto.Response> boardsToBoardResponse(List<Board> boards){

        final String url = "http://localhost:8080/boards";
           return boards
                   .stream()
                   .map(board -> {
                       List<AnswerDto.Response> answerResponses
                               = answersToAnswerResponses(board.getAnswers());
                       return BoardDto.Response
                               .builder()
                               .boardId(board.getBoardId())
                               .memberId(board.getMember().getMemberId())
                               .writer(board.getMember().getNickName())
                               .title(board.getTitle())
                               .body(board.getBody())
                               .createdAt(board.getCreatedAt())
                               .modifiedAt(board.getModifiedAt())
                               .like(board.getVote())
                               .answers(answerResponses)
                               .url(url+board.getBoardId())
                               .build();
                   }).collect(Collectors.toList());
    }
    default BoardDto.Response boardToBoardResponse(Board board){
        List<Answer> answerList = board.getAnswers();

        BoardDto.Response response = BoardDto.Response
                .builder()
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .writer(board.getMember().getNickName())
                .title(board.getTitle())
                .body(board.getBody())
                .boardId(board.getBoardId())
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .memberId(board.getMember().getMemberId())
                .answers(answersToAnswerResponses(answerList))
                .like(board.getVote())
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


