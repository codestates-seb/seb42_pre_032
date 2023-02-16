package BE.Server_BE.board.mapper;

import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    Board boardPostDtoToBoard(BoardDto.Post requestBody);
    Board boardPatchDtoToBoard(BoardDto.Patch requestBody);
    BoardDto.Response boardToBoardResponse(Board requestBody);
    List<BoardDto.Response> boardsToBoardResponse(List<Board> boards);

}
