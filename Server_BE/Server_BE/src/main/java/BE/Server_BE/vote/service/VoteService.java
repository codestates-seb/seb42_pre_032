package BE.Server_BE.vote.service;

import BE.Server_BE.advice.BusinessLogicException;
import BE.Server_BE.advice.ExceptionCode;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.vote.entity.Vote;
import BE.Server_BE.vote.repository.VoteRepository;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final AnswerService answerService;
    private final BoardService boardService;

    public VoteService(VoteRepository voteRepository,
                       AnswerService answerService,
                       BoardService boardService){
        this.voteRepository = voteRepository;
        this.answerService = answerService;
        this.boardService = boardService;
    }

    public void createBoardLike(long boardId, long memberId){
        // 좋아요는 한번만 할수있음
        voteRepository.getBoardVote(boardId, memberId).ifPresent(a -> {
            throw new BusinessLogicException(ExceptionCode.METHOD_NOT_ALLOWED);
        });

        //vote에 데이터 저장
        Board board = boardService.findBoard(boardId);
        Vote vote = new Vote();
        vote.setBoardVote(boardId, memberId, 1);
        voteRepository.save(vote);

        //answer 업데이트
        board.setVote(board.getVote()+1);
        boardService.updateBoard(board, memberId);
    }

    public void createBoardDislike(long boardId, long memberId){
        // 좋아요는 한번만 할수있음
        voteRepository.getBoardVote(boardId, memberId).ifPresent(a -> {
            throw new BusinessLogicException(ExceptionCode.METHOD_NOT_ALLOWED);
        });

        //vote에 데이터 저장
        Board board = boardService.findBoard(boardId);
        Vote vote = new Vote();
        vote.setBoardVote(boardId, memberId, -1);
        voteRepository.save(vote);

        //answer 업데이트
        board.setVote(board.getVote()-1);
        boardService.updateBoard(board, memberId);
    }

    public void createAnswerLike(long answerId, long memberId){
        // 좋아요는 한번만 할수있음
        voteRepository.getAnswerVote(answerId, memberId).ifPresent(a -> {
            throw new BusinessLogicException(ExceptionCode.METHOD_NOT_ALLOWED);
        });

        //vote에 데이터 저장
        Answer answer = answerService.getAnswer(answerId);
        Vote vote = new Vote();
        vote.setAnswerVote(answerId, memberId, 1);
        voteRepository.save(vote);

        //answer 업데이트
        answer.setVote(answer.getVote()+1);
        answerService.updateAnswer(answer);
    }

    public void createAnswerDislike(long answerId, long memberId){
        // 종아요하면 싫어요못함
        voteRepository.getAnswerVote(answerId, memberId).ifPresent(a -> {
            throw new BusinessLogicException(ExceptionCode.METHOD_NOT_ALLOWED);
        });

        Answer answer = answerService.getAnswer(answerId);
        Vote vote = new Vote();
        vote.setAnswerVote(answerId, memberId, -1);
        voteRepository.save(vote);

        answer.setVote(answer.getVote()-1);
        answerService.updateAnswer(answer);
    }

    public void deleteBoardVote(long boardId, long memberId){
        voteRepository.getBoardVote(boardId, memberId).ifPresent(vote -> {
            voteRepository.delete(vote);
            Board board = boardService.findBoard(boardId);
            board.setVote(board.getVote()-vote.getVote());
            boardService.updateBoard(board, memberId);
        });
    }
    public void deleteAnswerVote(long answerId, long memberId){
        voteRepository.getAnswerVote(answerId, memberId).ifPresent(vote -> {
            voteRepository.delete(vote);
            Answer answer = answerService.getAnswer(answerId);
            answer.setVote(answer.getVote()-vote.getVote());
            answerService.updateAnswer(answer);
        });
    }



}
