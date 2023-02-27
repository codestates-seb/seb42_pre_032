package BE.Server_BE.answer.controller;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.repository.AnswerRepository;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.mapper.BoardMapper;
import BE.Server_BE.board.service.BoardService;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.service.MemberService;
import BE.Server_BE.vote.service.VoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static BE.Server_BE.util.ApiDocumentUtils.getRequestPreProcessor;
import static BE.Server_BE.util.ApiDocumentUtils.getResponsePreProcessor;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BoardService boardService;

    @MockBean
    private MemberService memberService;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private VoteService voteService;

    @Mock
    private Principal principal;

    @MockBean
    private AnswerMapper answerMapper;

    @MockBean
    private AnswerRepository answerRepository;

    @MockBean
    private BoardMapper boardMapper;

    @Autowired
    private Gson gson;


    @Test
    public void postAnswer() throws Exception {

        Board board = new Board();
        board.setBoardId(1L);

        AnswerDto.Post post = new AnswerDto.Post("Test Title", "Test Body");
        String content = gson.toJson(post);

        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());
        given(answerMapper.answerPostToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());
        given(boardService.findVerifiedBoard(Mockito.any(Long.class))).willReturn(board);

        List<CommentDto.Response> responses = new ArrayList<>();
        AnswerDto.Response responseDto =
                new AnswerDto.Response(
                        1L,
                        1,
                        1,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "Test Title",
                        "Test Body",
                        0,
                        "http://localhost:8080/answers/1",
                        responses
                );

        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(new Answer());
        given(answerMapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(responseDto);

        // when
        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(
                        post("/answers/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .principal(principal)
                                .content(content)
                );
        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.body").value(post.getBody()))
                .andDo(document(
                        "post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("답글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("답글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답글 내용"),
                                        fieldWithPath("like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글 목록")
                                )
                        )
                ));
    }
    @Test
    public void patchAnswerTest() throws Exception {
        // given
        long answerId = 1L;

        AnswerDto.Patch patch = new AnswerDto.Patch(1,"답글 제목","답글 내용");
        String content = gson.toJson(patch);

        List<CommentDto.Response> responses = new ArrayList<>();
        AnswerDto.Response responseDto =
                new AnswerDto.Response(
                        1L,
                        1,
                        1,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "답글 제목",
                        "답글 내용",
                        0,
                        "http://localhost:8080/answers/1",
                        responses
                );
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());
        given(answerMapper.answerPatchToAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(new Answer());
        given(answerMapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(responseDto);

        // when
        // patch 파라미터에 principal이 들어감.
        when(principal.getName()).thenReturn("test@test");

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.patch("/answers/{answer-id}",answerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(principal)
                        .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerId").value(patch.getAnswerId()))
                .andExpect(jsonPath("$.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.body").value(patch.getBody()))
                .andDo(document(
                        "patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("답글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("답글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답글 내용"),
                                        fieldWithPath("like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글 목록")

                                )
                        )
                ));
    }
    @Test
    public void getAnswerTest() throws Exception {
        long answerId = 1L;
        Answer answer = new Answer(1,"답글 제목", "답글 내용",0);

        List<CommentDto.Response> commentResponses = new ArrayList<>();
        AnswerDto.Response response =
                new AnswerDto.Response(
                        1L,
                        1L,
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "답글 제목",
                        "답글 내용",
                        0,
                        "http://localhost:8080/answers/1",
                        commentResponses);

        given(answerService.findAnswer(Mockito.any(Long.class))).willReturn(new Answer());
        given(answerMapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/answers/{answer-id}", answerId)
                        .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerId").value(answer.getAnswerId()))
                .andExpect(jsonPath("$.title").value(answer.getTitle()))
                .andExpect(jsonPath("$.body").value(answer.getBody()))
                .andExpect(jsonPath("$.like").value(answer.getVote()))
                .andDo(document(
                        "get-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("답글 제목"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답글 내용"),
                                        fieldWithPath("like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url"),
                                        fieldWithPath("comments").type(JsonFieldType.ARRAY).description("댓글 목록")
                                )
                        )

                ));
    }
    @Test
    public void getAnswersTest() throws Exception {
        long answerId1 = 1L;
        Answer answer1 = new Answer(answerId1, "답글제목1", "답글제목1",0);
        List<CommentDto.Response> responses1 = new ArrayList<>();

        long answerId2 = 2L;
        Answer answer2 = new Answer(answerId1, "답글제목2", "답글제목2",0);
        List<CommentDto.Response> responses2 = new ArrayList<>();

        Page<Answer> pageAnswers =
                new PageImpl<>(List.of(answer1, answer2),
                        PageRequest.of(0,15, Sort.by("answerId").descending()), 2);

        List<AnswerDto.Response> responses = List.of(
                new AnswerDto.Response(
                        2L,
                        1L,
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "답글 제목",
                        "답글 내용",
                        0,
                        "http://localhost:8080/answers/1",
                        responses1),
                new AnswerDto.Response(
                        2L,
                        1L,
                        1L,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "답글 제목",
                        "답글 내용",
                        0,
                        "http://localhost:8080/answers/2",
                        responses2)
        );
        given(answerService.getAnswers(Mockito.any(Integer.class))).willReturn(pageAnswers);
        given(answerMapper.answersToAnswerResponses(Mockito.any(List.class))).willReturn(responses);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/answers")
                        .accept(MediaType.APPLICATION_JSON));
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
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("답글 제목"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("답글 내용"),
                                        fieldWithPath("data[].like").type(JsonFieldType.NUMBER).description("좋아요"),
                                        fieldWithPath("data[].url").type(JsonFieldType.STRING).description("url"),
                                        fieldWithPath("data[].comments").type(JsonFieldType.ARRAY).description("댓글 목록"),
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
    public void deleteAnswerTest() throws Exception {
        long answerId = 1L;

        doNothing().when(answerService).deleteAnswer(answerId);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/answers/{answer-id}",answerId));

        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답글 식별자")
                        )
                ));
    }
    @Test
    public void postAnswerLikeTest() throws Exception {
        long answerId = 1L;
        long memberId = 1L;

        doNothing().when(voteService).createAnswerLike(answerId,memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.post("/answers/{answer-id}/like", answerId)
                        .principal(principal));

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "post-answer-like",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답글 식별자")
                        )
                ));
    }
    @Test
    public void postAnswerDislikeTest() throws Exception {
        long answerId = 1L;
        long memberId = 1L;

        doNothing().when(voteService).createAnswerDislike(answerId, memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.post("/answers/{answer-id}/dislike", answerId)
                        .principal(principal));

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "post-answer-dislike",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답글 식별자")
                        )
                ));
    }
    @Test
    public void deleteAnswerLikeTest() throws Exception{
        long memberId = 1L;
        long answerId = 1L;

        doNothing().when(voteService).deleteAnswerVote(answerId,memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/answers/{answer-id}",answerId)
                        .principal(principal));
        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-answer-like",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답글 식별자")
                        )
                ));
    }
    @Test
    public void deleteAnswerDislikeTest() throws Exception{
        long memberId = 1L;
        long answerId = 1L;

        doNothing().when(voteService).deleteAnswerVote(answerId, memberId);
        given(memberService.findMemberByEmail(Mockito.any(String.class))).willReturn(new Member());

        when(principal.getName()).thenReturn("test@test");
        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/answers/{answer-id}",answerId)
                        .principal(principal));

        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-answer-dislike",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("answer-id").description("답글 식별자")
                        )
                ));
    }




}
