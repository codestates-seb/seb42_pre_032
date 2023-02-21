package BE.Server_BE.board.service;

import BE.Server_BE.advice.BusinessLogicException;
import BE.Server_BE.advice.ExceptionCode;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.repository.BoardRepository;
import BE.Server_BE.member.repository.MemberRepository;
import BE.Server_BE.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;


@Service
public class BoardService {
    private BoardRepository boardRepository;
    private MemberService memberService;


    public BoardService(BoardRepository boardRepository, MemberService memberService, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberService = memberService;
    }

    public Board createBoard(Board board) {
        Board savedBoard = boardRepository.save(board);
        return savedBoard;
    }

    public Board updateBoard(Board board, long memberId) {
        Board findBoard = findVerifiedBoard(board.getBoardId());

        // 글을 작성한 사용자만 글을 삭제할수잇다(근데 관리자는 어케삭제하지)
        if(memberId != findBoard.getMember().getMemberId())
            throw new BusinessLogicException(ExceptionCode.METHOD_NOT_ALLOWED);

                Optional.ofNullable(board.getTitle())
                        .ifPresent(findBoard::setTitle);
                Optional.ofNullable(board.getBody())
                        .ifPresent(findBoard::setBody);

        return boardRepository.save(findBoard);
    }

    public Board findBoard(long boardId) {
        return findVerifiedBoard(boardId);
    }
    public Board findBoard(long boardId, Principal principal) {
        Board board = findVerifiedBoard(boardId);
        return board;
    }

    @Transactional(readOnly = true)
    public Page<Board> findBoards(Pageable pageable) {
        return boardRepository.findAll(PageRequest.of(1,15, Sort.by("boardId").descending()));
    }

    public void deleteBoard(long boardId) {
        Board findBoard = findVerifiedBoard(boardId);
        boardRepository.delete(findBoard);
    }

    public Board findVerifiedBoard(long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board findBoard = optionalBoard.orElseThrow(()->
                new BusinessLogicException(ExceptionCode.DATA_IS_EMPTY));
        return findBoard;
    }
}
