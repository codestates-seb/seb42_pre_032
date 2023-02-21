package BE.Server_BE.board.mapper;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;

import BE.Server_BE.comment.dto.CommentDto;

import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;

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

    default BoardDto.Response boardToBoardResponse(Board board){

        BoardDto.Response response = BoardDto.Response
                .builder()
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .writer(board.getMember().getNickName())
                .title(board.getTitle())
                .body(board.getBody())
                .boardId(board.getBoardId())
                .memberId(board.getMember().getMemberId())
                
//                .answers(board.getAnswers())
                .like(board.getVote())
                .build();

        return response;
    }
    


    List<BoardDto.Response> boardsToBoardResponse(List<Board> boards);
=======
    default List<BoardDto.Response> boardsToBoardResponse(List<Board> boards){
        return boards
                .stream()
                .map(board -> BoardDto.Response
                        .builder()
                        .boardId(board.getBoardId())
                        .title(board.getTitle())
                        .body(board.getBody())
//                .answers(board.getAnswers())
                        .writer(board.getMember().getNickName())
                        .like(board.getVote())
                        .build())
                .collect(Collectors.toList());
    }
}
