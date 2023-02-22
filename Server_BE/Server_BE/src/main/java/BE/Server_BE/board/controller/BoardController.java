package BE.Server_BE.board.controller;

import BE.Server_BE.MultiResponse;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.mapper.BoardMapper;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;
import BE.Server_BE.springsecurity.HelloUserDetailsService;
import BE.Server_BE.vote.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;

@Validated
@RestController
@RequestMapping("/boards")
@Slf4j
public class BoardController {
    public static String url = "http://localhost:8080/boards/";
    private final BoardService boardService;
    private final BoardMapper boardMapper;
    private final VoteService voteService;
    private final MemberService memberService;

    public BoardController(BoardService boardService,
                           BoardMapper boardMapper,
                           VoteService voteService,
                           MemberService memberService) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
        this.voteService = voteService;
        this.memberService = memberService;
    }
    @PostMapping
    public ResponseEntity postBoard(@RequestBody BoardDto.Post requestBody,
                                    Principal principal) {

        Member member = memberService.findMemberByEmail(principal.getName());
        Board board = boardMapper.boardPostDtoToBoard(requestBody);
        board.setMember(member);
        board.setVote(0L);
        Board postedBoard = boardService.createBoard(board);

        BoardDto.Response response = boardMapper.boardToBoardResponse(postedBoard);
        response.setUrl(url+response.getBoardId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@PathVariable("board-id") @Positive long boardId,
                                     @Valid @RequestBody BoardDto.Patch requestBody,
                                     Principal principal) {
        Member member = memberService.findMemberByEmail(principal.getName());
        Board board = boardMapper.boardPatchDtoToBoard(requestBody);
        board.setMember(member);
        board.setBoardId(boardId);
        Board updatedBoard = boardService.updateBoard(board, board.getMember().getMemberId());
        BoardDto.Response response = boardMapper.boardToBoardResponse(updatedBoard);
        response.setUrl(url+response.getBoardId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") @Positive long boardId) {
        Board board = boardService.findBoard(boardId);
        BoardDto.Response response = boardMapper.boardToBoardResponse(board);
        response.setUrl(url+response.getBoardId());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getBoards(@Positive @RequestParam(required = false, defaultValue = "1") int page) {
        Page<Board> pageBoards = boardService.findBoards(page);
        PageInfo pageInfo = new PageInfo(pageBoards.getNumber(), pageBoards.getSize(),
                pageBoards.getTotalElements(),pageBoards.getTotalPages());
        List<Board> boards = pageBoards.getContent();
        List<BoardDto.Response> responses = boardMapper.boardsToBoardResponse(boards);
        responses.stream().forEach(b -> b.setUrl(url+b.getBoardId()));

        return new ResponseEntity(
                new MultiResponse(responses, pageInfo),  HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity deleteBoard(@PathVariable("board-id") @Positive long boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{board-id}/like")
    public ResponseEntity postLike (@PathVariable("board-id") @Positive long boardId,
                                    Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        voteService.createBoardLike(boardId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/{board-id}/dislike")
    public ResponseEntity postDislike (@PathVariable("board-id") @Positive long boardId,
                                       Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        voteService.createBoardDislike(boardId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{board-id}/like")
    public ResponseEntity deleteLike (@PathVariable("board-id") @Positive long boardId,
                                    Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        voteService.deleteBoardVote(boardId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{board-id}/dislike")
    public ResponseEntity deleteDislike (@PathVariable("board-id") @Positive long boardId,
                                       Principal principal){
        Member member = memberService.findMemberByEmail(principal.getName());
        voteService.deleteBoardVote(boardId, member.getMemberId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
