package BE.Server_BE.board.mapper;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.comment.dto.CommentDto;
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
//    BoardDto.Response boardToBoardResponse(Board requestBody);

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
                .build();

        return response;
    }

    List<BoardDto.Response> boardsToBoardResponse(List<Board> boards);

}
