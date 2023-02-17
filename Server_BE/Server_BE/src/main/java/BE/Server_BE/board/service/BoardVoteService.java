package BE.Server_BE.board.service;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.entity.AnswerVote;
import BE.Server_BE.answer.repository.AnswerVoteRepository;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.entity.BoardVote;
import BE.Server_BE.board.repository.BoardVoteRepository;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class BoardVoteService {
    private final BoardVoteRepository boardVoteRepository;
    private final BoardService boardService;
    private final MemberService memberService;

    public BoardVoteService(BoardVoteRepository boardVoteRepository, BoardService boardService, MemberService memberService) {
        this.boardVoteRepository = boardVoteRepository;
        this.boardService = boardService;
        this.memberService = memberService;
    }

    public void createLike(long boardId, long memberId){
        Board board = boardService.findBoard(boardId);
        Member member = memberService.loadMember(memberId);
        BoardVote vote = new BoardVote(member, board, 1);

        boardVoteRepository.save(vote);
    }

    public void createDislike(long boardId, long memberId){
        Board board = boardService.findBoard(boardId);
        Member member = memberService.loadMember(memberId);
        BoardVote vote = new BoardVote(member, board, -1);

        boardVoteRepository.save(vote);
    }

    public long getVote(long boardId){
        return boardVoteRepository.getVoteCount(boardId);
    }
}
