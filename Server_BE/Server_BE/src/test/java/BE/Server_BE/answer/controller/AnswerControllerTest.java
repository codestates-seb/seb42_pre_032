package BE.Server_BE.answer.controller;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.mapper.AnswerMapper;
import BE.Server_BE.answer.service.AnswerService;
import BE.Server_BE.board.dto.BoardDto;
import BE.Server_BE.board.entity.Board;
import BE.Server_BE.board.mapper.BoardMapper;
import BE.Server_BE.member.dto.MemberDto;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.mapper.MemberMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

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
        MemberDto.Post memberPost = new MemberDto.Post("아이언맨","IronMan@marvel.com","123123","아이언맨입니다.");
        Member member = memberMapper.memberDtoPostToMember(memberPost);
        member.setMemberId(1L);


        BoardDto.Post boardPost = new BoardDto.Post("게시글 제목", "게시글 내용");
        Board board = boardMapper.boardPostDtoToBoard(boardPost);
        board.setBoardId(1L);


        AnswerDto.Post answerPost = new AnswerDto.Post("답글 제목", "답글 내용");
        Answer answer = answerMapper.answerPostToAnswer(answerPost);
        answer.setAnswerId(1L);


        given(answerService.createAnswer(Mockito.any(Answer.class)))
                .willReturn(answer);


        String content = gson.toJson(answerPost);


        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/answers/1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        actions.andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/answers/1"))));

        //then



    }
}
