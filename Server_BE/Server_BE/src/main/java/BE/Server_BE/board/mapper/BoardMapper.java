package BE.Server_BE.board.mapper;

import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    Board boardPostDtoToBoard(BoardDto.Post requestBody);
    Board boardPatchDtoToBoard(BoardDto.Patch requestBody);

    default BoardDto.Response boardToBoardResponse(Board board){
        BoardDto.Response response =BoardDto.Response
                .builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .body(board.getBody())
//                .answers(board.getAnswers())
                .writer(board.getMember().getNickName())
                .like(board.getVote())
                .build();

        return response;
    }
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
