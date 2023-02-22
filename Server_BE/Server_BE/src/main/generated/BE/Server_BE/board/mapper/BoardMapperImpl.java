package BE.Server_BE.board.mapper;

import BE.Server_BE.board.dto.BoardDto.Patch;
import BE.Server_BE.board.dto.BoardDto.Post;
import BE.Server_BE.board.dto.BoardDto.Response;
import BE.Server_BE.board.entity.Board;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-22T17:17:43+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board boardPostDtoToBoard(Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Board board = new Board();

        board.setTitle( requestBody.getTitle() );
        board.setBody( requestBody.getBody() );

        return board;
    }

    @Override
    public Board boardPatchDtoToBoard(Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Board board = new Board();

        board.setBoardId( requestBody.getBoardId() );
        board.setTitle( requestBody.getTitle() );
        board.setBody( requestBody.getBody() );

        return board;
    }

    @Override
    public List<Response> boardsToBoardResponse(List<Board> boards) {
        if ( boards == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( boards.size() );
        for ( Board board : boards ) {
            list.add( boardToBoardResponse( board ) );
        }

        return list;
    }
}
