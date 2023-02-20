package BE.Server_BE.board.controller;

import BE.Server_BE.MultiResponse;
import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.mapper.BoardMapper;
import BE.Server_BE.board.service.BoardVoteService;
import BE.Server_BE.board.utils.UriCreator;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;
import BE.Server_BE.springsecurity.HelloUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.security.Principal;
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
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;

    public BoardController(BoardService boardService,
                           BoardMapper boardMapper,
                           BoardVoteService boardVoteService,
                           AnswerService answerService,
                           AnswerMapper answerMapper,
                           MemberService memberService) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.boardVoteService = boardVoteService;
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.memberService = memberService;
    }
    // 보드 생성하려면 memberId가 필요하거나
    // User 권한이 있으면 되는데 BoardDto.Post에는
    // 포함되어있지 않고 따로 authority도 포함되어 있지 않다.
    // 어떻게 수정해야하는가 ?
    @PostMapping

    public ResponseEntity postBoard(@RequestBody BoardDto.Post requestBody,
                                    @AuthenticationPrincipal HelloUserDetailsService.HelloUserDetails userDetails) {
        Board createdBoard = boardMapper.boardPostDtoToBoard(requestBody);
        createdBoard.setMember(memberService.loadMember(userDetails.getMemberId()));
        Board board = boardService.createBoard(createdBoard);


        return new ResponseEntity<>(boardMapper.boardToBoardResponse(board), HttpStatus.OK);
    }

    @PostMapping("/{board-id}")
    public ResponseEntity postAnswer(@PathVariable("board-id") @Positive long boardId,
                                     @Valid @RequestBody AnswerDto.Post requestBody,
                                     @AuthenticationPrincipal HelloUserDetailsService.HelloUserDetails userDetails) {

        Answer answer = answerMapper.answerPostToAnswer(requestBody);
        answer.setMember(memberService.loadMember(userDetails.getMemberId()));
        answer.setBoard(boardService.findBoard(boardId));

        answerService.createAnswer(answer);
        return new ResponseEntity<>(answerMapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@PathVariable("board-id") @Positive long boardId,
                                     @Valid @RequestBody BoardDto.Patch requestBody,
                                     @AuthenticationPrincipal HelloUserDetailsService.HelloUserDetails userDetails) {
        requestBody.setBoardId(boardId);
        Board board = boardService.updateBoard(boardMapper.boardPatchDtoToBoard(requestBody), userDetails.getMemberId());

        return new ResponseEntity(boardMapper.boardToBoardResponse(board), HttpStatus.OK);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") @Positive long boardId) {
        Board board = boardService.findBoard(boardId);
        return new ResponseEntity(boardMapper.boardToBoardResponse(board), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getBoards(@Positive @RequestParam(required = false, defaultValue = "1") int page) {
        Page<Board> pageBoards = boardService.findBoards(page);
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
