package BE.Server_BE.board.controller;

import BE.Server_BE.MultiResponse;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.mapper.BoardMapper;
import BE.Server_BE.board.service.BoardVoteService;
import BE.Server_BE.board.utils.UriCreator;
import BE.Server_BE.member.response.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/boards")
@Slf4j
public class BoardController {
    public static String BOARD_DEFAULT_URL = "/boards";
    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final BoardVoteService boardVoteService;



    public BoardController(BoardService boardService,
                           BoardMapper boardMapper,
                           BoardVoteService boardVoteService) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.boardVoteService = boardVoteService;
    }

    @PostMapping
    public ResponseEntity postBoard(@RequestBody BoardDto.Post requestBody) {

        Board board = boardService.createBoard(boardMapper.boardPostDtoToBoard(requestBody));
        URI location = UriCreator.createUri(BOARD_DEFAULT_URL, board.getBoardId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@PathVariable("board-id") @Positive long boardId,
                                     @Valid @RequestBody BoardDto.Patch requestBody) {
        requestBody.setBoardId(boardId);
        Board board = boardService.updateBoard(boardMapper.boardPatchDtoToBoard(requestBody));

        return new ResponseEntity(boardMapper.boardToBoardResponse(board), HttpStatus.OK);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") @Positive long boardId) {
        Board board = boardService.findBoard(boardId);
        return new ResponseEntity(boardMapper.boardToBoardResponse(board), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getBoards(Pageable pageable) {
        Page<Board> pageBoards = boardService.findBoards(pageable);
        PageInfo pageInfo = new PageInfo(pageBoards.getNumber(), pageBoards.getSize(),
                pageBoards.getTotalElements(),pageBoards.getTotalPages());
        List<Board> boards = pageBoards.getContent();
        return new ResponseEntity(
                new MultiResponse(boardMapper.boardsToBoardResponse(boards), pageInfo),  HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id") @Positive long boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{board-id}/like")
    public ResponseEntity postLike (@PathVariable("board-id") @Positive long boardId){
        boardVoteService.createLike(boardId, 1);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/{board-id}/dislike")
    public ResponseEntity postDislike (@PathVariable("board-id") @Positive long boardId){
        boardVoteService.createDislike(boardId, 1);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{board-id}/vote")
    public ResponseEntity getVote (@PathVariable("board-id") @Positive long boardId){

        return new ResponseEntity<>(boardVoteService.getVote(boardId),HttpStatus.OK);
    }
}
