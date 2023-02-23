package BE.Server_BE.answer.controller;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.board.mapper.BoardMapper;
import BE.Server_BE.comment.dto.CommentDto;
import BE.Server_BE.member.mapper.MemberMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static BE.Server_BE.member.util.ApiDocumentUtils.getRequestPreProcessor;
import static BE.Server_BE.member.util.ApiDocumentUtils.getResponsePreProcessor;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @Mock
    private Principal principal;

    @MockBean
    private AnswerMapper answerMapper;

    @MockBean
    private MemberMapper memberMapper;

    @MockBean
    private BoardMapper boardMapper;

    @Autowired
    private Gson gson;

    @Test
    public void postAnswerTest() throws Exception {
        // given

        
        AnswerDto.Post answerPost = new AnswerDto.Post("답글 제목", "답글 내용");
        String content = gson.toJson(answerPost);

        List<CommentDto.Response> responses = new ArrayList<>();

        AnswerDto.Response response =
                new AnswerDto.Response(
                        1L,
                        1L,
                        1L,
                        "답글 내용",
                        "답글 제목",
                        0L,
                        responses,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        "http://localhost:8080/answers/1"

                );

        given(answerMapper.answerPostToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());
        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(new Answer());
        given(answerMapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(response);

        // when
        when(principal.getName()).thenReturn("123@123");
        ResultActions actions =
                mockMvc.perform(
                        post("/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .principal(principal)
                                .content(content)
                );
        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(answerPost.getTitle()))
                .andExpect(jsonPath("$.body").value(answerPost.getBody()))
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
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답글 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("boardId").type(JsonFieldType.NUMBER).description("게시글 식별자"),
                                        fieldWithPath("body").type(JsonFieldType.STRING).description("답글 내용"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("답글 제목"),
                                        fieldWithPath("like").type(JsonFieldType.NUMBER).description("답글 좋아요"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")
                                )
                        )
                ));
    }
}
