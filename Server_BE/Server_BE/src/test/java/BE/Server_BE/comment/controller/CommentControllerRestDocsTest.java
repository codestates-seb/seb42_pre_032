package BE.Server_BE.comment.controller;

import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.comment.entity.Comment;
import BE.Server_BE.comment.mapper.CommentMapper;
import BE.Server_BE.comment.service.CommentService;
import BE.Server_BE.member.controller.MemberController;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.mapper.MemberMapper;
import BE.Server_BE.member.service.MemberService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static BE.Server_BE.comment.utill.ApiDocumentUtils.getRequestPreProcessor;
import static BE.Server_BE.comment.utill.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private CommentMapper commentMapper;
    @MockBean
    private CommentService commentService;
    @MockBean
    private MemberService memberService;
    @MockBean
    private AnswerService answerService;
    @Mock
    private Principal principal;

    @Test
    public void postCommentTest() throws Exception {
        Answer answer = new Answer();
        answer.setAnswerId(1L);
        CommentDto.Post post = new CommentDto.Post("테스트");
        String content = gson.toJson(post);

        CommentDto.Response responseDto = new CommentDto.Response(
                1,
                1,
                1,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "테스트",
                "http://localhost:8080/comments/1"
        );

        given(commentMapper.commentDtoPostToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());
        given(memberService.findMemberByEmail(Mockito.anyString())).willReturn(new Member());
        given(answerService.findAnswer(Mockito.any(Long.class))).willReturn(new Answer());
        given(commentService.createComment(Mockito.any(Comment.class))).willReturn(new Comment());
        given(commentMapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(responseDto);


        when(principal.getName()).thenReturn("test@test");


        ResultActions actions =
                mockMvc.perform(MockMvcRequestBuilders.post("/comments/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(principal)
                        .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.body").value(post.getBody()))
                .andDo(document("post-comment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("댓글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("댓글 내용"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")
                                )
                        )

                ));
    }

    @Test
    public void patchCommentTest() throws Exception {
        long commentId = 1L;

        CommentDto.Patch patch = new CommentDto.Patch("테스트");
        String content = gson.toJson(patch);

        CommentDto.Response responseDto =
                new CommentDto.Response(
                        1,
                        1,
                        1,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "테스트",
                        "http://localhost:8080/comments/1"
                );

        given(commentMapper.commentDtoPatchToComment(Mockito.any(CommentDto.Patch.class))).willReturn(new Comment());
        given(commentService.updateComment(Mockito.any(Comment.class))).willReturn(new Comment());
        given(commentMapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(responseDto);

        when(principal.getName()).thenReturn("test@test");

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.patch("/comments/{comment-id}", commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(principal)
                        .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body").value(patch.getBody()))
                .andDo(document("patch-comment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("comment-id").description("댓글 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("댓글 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("댓글 내용"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")
                                )
                        )
                ));
    }

    @Test
    public void getCommentTest() throws Exception {
        long commentId = 1L;
        Comment comment = new Comment("테스트");


        CommentDto.Response responseDto =
                new CommentDto.Response(
                        1,
                        1,
                        1,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "테스트",
                        "http://localhost:8080/comments/1"
                );

        given(commentService.getComment(Mockito.anyLong())).willReturn(new Comment());
        given(commentMapper.commentToCommentDtoResponse(Mockito.any(Comment.class))).willReturn(responseDto);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/comments/{comment-id}", commentId)
                        .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body").value(comment.getBody()))
                .andDo(document("get-comment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("comment-id").description("댓글 식별자")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("댓글 내용"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")
                                )
                        )
                ));
    }

    @Test
    public void getCommentsTest() throws Exception {
        long commentId1 = 1L;
        Comment comment1 = new Comment(commentId1, "테스트1");

        long commentId2 = 2L;
        Comment comment2 = new Comment(commentId2, "테스트2");

        Page<Comment> commentPage =
                new PageImpl<>(List.of(comment1, comment2),
                        PageRequest.of(0, 15, Sort.by("commentId").descending()), 2);

        List<CommentDto.Response> responses = List.of(
                new CommentDto.Response(
                        1,
                        1,
                        1,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "테스트1",
                        "http://localhost:8080/comments/1"
                ),
                new CommentDto.Response(
                        2,
                        1,
                        1,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "테스트2",
                        "http://localhost:8080/comments/1"
                )
        );

        given(commentService.getComments(Mockito.anyInt())).willReturn(commentPage);
        given(commentMapper.commentsToCommentDtoResponse(Mockito.any(List.class))).willReturn(responses);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.get("/comments")
                        .accept(MediaType.APPLICATION_JSON));

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-comments",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("수정 시간"),
                                        fieldWithPath("data[].body").type(JsonFieldType.STRING).description("댓글 내용"),
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
    public void deleteCommentTest() throws Exception {
        long commentId = 1L;

        doNothing().when(commentService).deleteComment(commentId);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/comments/{comment-id}", commentId));

        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-comment",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("comment-id").description("댓글 식별자")
                        )
                ));
    }
}