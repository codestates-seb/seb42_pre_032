package BE.Server_BE.board.controller;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.mapper.BoardMapper;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.service.MemberService;
import BE.Server_BE.vote.service.VoteService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static BE.Server_BE.member.util.ApiDocumentUtils.getRequestPreProcessor;
import static BE.Server_BE.member.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BoardController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
public class BoardControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @Mock
    private Principal principal;

    @MockBean
    private BoardService boardService;

    @MockBean
    private VoteService voteService;


    @MockBean
    private MemberService memberService;

    @MockBean
    private BoardMapper mapper;

    @Test
    public void postBoardTest() throws Exception {
        //given

        BoardDto.Post post = new BoardDto.Post("Test Title", "Test Body");
        String content = gson.toJson(post);

        List<AnswerDto.Response> responses = new ArrayList<>();
        BoardDto.Response responseDto =
                new BoardDto.Response(
                        1,
                        1,
                        "글쓴이",
                        "Test Title",
                        "Test Body",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        responses,
                        "http://localhost:8080/boards/1"
                );

        given(mapper.boardPostDtoToBoard(Mockito.any(BoardDto.Post.class))).willReturn(new Board());
        given(boardService.createBoard(Mockito.any(Board.class))).willReturn(new Board());
        given(mapper.boardToBoardResponse(Mockito.any(Board.class))).willReturn(responseDto);

        //when
        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(
                        post("/boards")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .principal(principal)
                                .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.body").value(post.getBody()))
                .andDo(document(
                        "post-board",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("게시글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("writer").type(JsonFieldType.STRING).description("글쓴이"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("게시글 내용"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답글 목록"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")

                                )
                        )
                ));
    }

    @Test
    public void patchBoardTest() throws Exception {
        //given
        long boardId = 1L;

        BoardDto.Patch patch = new BoardDto.Patch(1, 1,"Test Title", "Test Body");
        String content = gson.toJson(patch);

        List<AnswerDto.Response> responses = new ArrayList<>();
        BoardDto.Response responseDto =
                new BoardDto.Response(
                        1,
                        1,
                        "글쓴이",
                        "Test Title",
                        "Test Body",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        responses,
                        "http://localhost:8080/boards/1"
                );

        given(mapper.boardPatchDtoToBoard(Mockito.any(BoardDto.Patch.class))).willReturn(new Board());
        given(boardService.updateBoard(Mockito.any(Board.class), Mockito.anyLong())).willReturn(new Board());
        given(mapper.boardToBoardResponse(Mockito.any(Board.class))).willReturn(responseDto);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        //when
        when(principal.getName()).thenReturn("test@test");

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.patch("/boards/{board-id}", boardId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .principal(principal)
                                .content(content)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(patch.getMemberId()))
                .andExpect(jsonPath("$.boardId").value(patch.getBoardId()))
                .andExpect(jsonPath("$.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.body").value(patch.getBody()))
                .andDo(document(
                        "patch-board",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("board-id").description("게시글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("게시글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("writer").type(JsonFieldType.STRING).description("글쓴이"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("게시글 내용"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답글 목록"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")

                                )
                        )
                ));
    }

    @Test
    public void getBoardTest() throws Exception {
        //given
        long boardId = 1L;

        Board board = new Board(1, "Test Title", "Test Body", 0);

        List<AnswerDto.Response> responses = new ArrayList<>();
        BoardDto.Response responseDto =
                new BoardDto.Response(
                        1,
                        1,
                        "글쓴이",
                        "Test Title",
                        "Test Body",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        responses,
                        "http://localhost:8080/boards/1"
                );

        given(boardService.findBoard(Mockito.anyLong())).willReturn(new Board());
        given(mapper.boardToBoardResponse(Mockito.any(Board.class))).willReturn(responseDto);

        //when
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/boards/{board-id}", boardId)
                        .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.boardId").value(board.getBoardId()))
                .andExpect(jsonPath("$.title").value(board.getTitle()))
                .andExpect(jsonPath("$.body").value(board.getBody()))
                .andExpect(jsonPath("$.like").value(board.getVote()))
                .andDo(document(
                        "get-board",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("board-id").description("게시글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("writer").type(JsonFieldType.STRING).description("글쓴이"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("게시글 내용"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답글 목록"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")

                                )
                        )
                ));
    }

    @Test
    public void getBoardsTest() throws Exception {
        //given
        long boardId1 = 1L;
        Board board1 = new Board(boardId1, "Test Title", "Test Body", 0);
        List<AnswerDto.Response> responses1 = new ArrayList<>();

        long boardId2= 1L;
        Board board2 = new Board(boardId2, "Test Title", "Test Body", 0);
        List<AnswerDto.Response> responses2 = new ArrayList<>();

        Page<Board> pageBoards =
                new PageImpl<>(List.of(board1, board2),
                        PageRequest.of(0,15, Sort.by("boardId").descending()), 2);

        List<BoardDto.Response> responses = List.of(
                new BoardDto.Response(
                        1,
                        1,
                        "글쓴이",
                        "Test Title",
                        "Test Body",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        responses1,
                        "http://localhost:8080/boards/1"
                ),
                new BoardDto.Response(
                        1,
                        1,
                        "글쓴이",
                        "Test Title",
                        "Test Body",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0,
                        responses2,
                        "http://localhost:8080/boards/1"
                )
        );

        given(boardService.findBoards(Mockito.anyInt())).willReturn(pageBoards);
        given(mapper.boardsToBoardResponse(Mockito.anyList())).willReturn(responses);

        //when
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/boards")
                        .accept(MediaType.APPLICATION_JSON)
                );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document(
                        "get-boards",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].writer").type(JsonFieldType.STRING).description("글쓴이"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("게시글 제목"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("게시글 내용"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("data[].like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("data[].answers").type(JsonFieldType.ARRAY).description("답글 목록"),
                                        fieldWithPath("data[].url").type(JsonFieldType.STRING).description("url"),
                                        fieldWithPath("pageinfo").type(JsonFieldType.OBJECT).description("페이지 데이터"),
                                        fieldWithPath("pageinfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageinfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                        fieldWithPath("pageinfo.totalElements").type(JsonFieldType.NUMBER).description("데이터 개수"),
                                        fieldWithPath("pageinfo.totalPages").type(JsonFieldType.NUMBER).description("모든 페이지")

                                )
                        )
                ));
    }

    @Test
    public void deleteBoardTest() throws Exception {
        //given
        long boardId = 1L;

        doNothing().when(boardService).deleteBoard(boardId);

        //when
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/boards/{board-id}", boardId));

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-board",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("board-id").description("게시글 식별자")
                        )

                ));
    }

    @Test
    public void postBoardLikeTest() throws Exception {
        //given
        long boardId = 1L;
        long memberId = 1L;

        doNothing().when(voteService).createBoardLike(boardId, memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        //when
        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.post("/boards/{board-id}/like", boardId)
                        .principal(principal));

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "post-board-like",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("board-id").description("게시글 식별자")
                        )
                ));
    }

    @Test
    public void postBoardDislikeTest() throws Exception {
        //given
        long boardId = 1L;
        long memberId = 1L;

        doNothing().when(voteService).createBoardDislike(boardId, memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        //when
        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.post("/boards/{board-id}/dislike", boardId)
                        .principal(principal));

        //then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "post-board-dislike",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("board-id").description("게시글 식별자")
                        )
                ));
    }
    @Test
    public void deleteBoardLikeTest() throws Exception {
        //given
        long boardId = 1L;
        long memberId = 1L;

        doNothing().when(voteService).deleteBoardVote(boardId, memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        //when
        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/boards/{board-id}/like", boardId)
                        .principal(principal));

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-board-like",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("board-id").description("게시글 식별자")
                        )
                ));
    }

    @Test
    public void deleteBoardDisikeTest() throws Exception {
        //given
        long boardId = 1L;
        long memberId = 1L;

        doNothing().when(voteService).deleteBoardVote(boardId, memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        //when
        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/boards/{board-id}/dislike", boardId)
                        .principal(principal));

        //then
        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-board-dislike",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("board-id").description("게시글 식별자")
                        )
                ));
    }
}
