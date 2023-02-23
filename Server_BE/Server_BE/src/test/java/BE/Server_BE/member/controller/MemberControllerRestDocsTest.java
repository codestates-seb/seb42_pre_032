package BE.Server_BE.member.controller;

import BE.Server_BE.member.dto.MemberDto;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.mapper.MemberMapper;

import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.access.method.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static BE.Server_BE.member.util.ApiDocumentUtils.getRequestPreProcessor;
import static BE.Server_BE.member.util.ApiDocumentUtils.getResponsePreProcessor;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
//@SpringBootTest
//@AutoConfigureMockMvc
public class MemberControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;
    @MockBean
    private MemberMapper mapper;

    @Test
    public void postMemberTest() throws Exception {
        //given
        MemberDto.Post post = new MemberDto.Post("Paul", "Paul@gmail.com", "Paul", "Paul");
        String content = gson.toJson(post);

        MemberDto.Response responseDto =
                new MemberDto.Response(1,
                        "Paul",
                        "Paul@gmail.com",
                        "Paul",
                        "Paul",
                        "http://localhost:8080/members/1");


        given(mapper.memberDtoPostToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberDtoResponse(Mockito.any(Member.class))).willReturn(responseDto);


        //when
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.post("/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nickName").value(post.getNickName()))
                .andExpect(jsonPath("$.email").value(post.getEmail()))
                .andExpect(jsonPath("$.password").value(post.getPassword()))
                .andExpect(jsonPath("$.about_Me").value(post.getAbout_Me()))
                .andDo(document(
                        "post-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("about_Me").type(JsonFieldType.STRING).description("자기소개")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("about_Me").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")

                                )

                        )
                ));
    }


    @Test
    public void patchMemberTest() throws Exception {

        long memberId = 1L;
        MemberDto.Patch patch = new MemberDto.Patch(memberId, "John", "Paul@gmail.com", "John", "John");
        String content1 = gson.toJson(patch);


        MemberDto.Response responseDto1 =
                new MemberDto.Response(1,
                        "John",
                        "Paul@gmail.com",
                        "John",
                        "John",
                        "http://localhost:8080/members/1");

        given(mapper.memberDtoPatchToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToMemberDtoResponse(Mockito.any(Member.class))).willReturn(responseDto1);




        ResultActions actions1 =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.patch("/members/{member-id}", memberId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content1)
                );

        actions1
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(patch.getMemberId()))
                .andExpect(jsonPath("$.nickName").value(patch.getNickName()))
                .andExpect(jsonPath("$.email").value(patch.getEmail()))
                .andExpect(jsonPath("$.password").value(patch.getPassword()))
                .andExpect(jsonPath("$.about_Me").value(patch.getAbout_Me()))
                .andDo(document("patch-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("작성자").optional(),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일").optional(),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional(),
                                        fieldWithPath("about_Me").type(JsonFieldType.STRING).description("자기소개").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("about_Me").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")
                                )
                        )
                ));
    }

    @Test
    public void getMemberTest() throws Exception {

        long memberId = 1L;
        MemberDto.Patch patch = new MemberDto.Patch(memberId, "John", "Paul@gmail.com", "John", "John");

        ResultActions actions1 =
                mockMvc.perform(RestDocumentationRequestBuilders.patch("/members/{member-id}", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                );

        actions1
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(patch.getMemberId()))
                .andExpect(jsonPath("$.nickName").value(patch.getNickName()))
                .andExpect(jsonPath("$.email").value(patch.getEmail()))
                .andExpect(jsonPath("$.password").value(patch.getPassword()))
                .andExpect(jsonPath("$.about_Me").value(patch.getAbout_Me()))
                .andDo(document("patch-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("작성자").optional(),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일").optional(),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional(),
                                        fieldWithPath("about_Me").type(JsonFieldType.STRING).description("자기소개").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("about_Me").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("url").type(JsonFieldType.STRING).description("url")
                                )
                        )
                ));
    }

    @Test
    public void getMembersTest() throws Exception {
        long firstMemberId = 1L;
        Member post = new Member(firstMemberId,"Paul", "Paul@gmail.com", "Paul", "Paul");

        long secondMemberId = 2L;
        Member post1 = new Member(secondMemberId, "Anne", "Anne@gmail.com", "Anne", "Anne");



        Page<Member> memberPages = new PageImpl<>(List.of(
                post, post1), PageRequest.of(0, 15, Sort.by("memberId").descending()), 2);

        List<MemberDto.Response> responses = List.of(
                new MemberDto.Response(
                        firstMemberId,
                        "Paul",
                        "Paul@gmail.com",
                        "Paul",
                        "Paul",
                        "http://localhost:8080/members/1"
                ),

                new MemberDto.Response(
                        secondMemberId,
                        "Anne",
                        "Anne@gmail.com",
                        "Anne",
                        "Anne",
                        "http://localhost:8080/members/2"

                )

        );

        given(memberService.getMembers(Mockito.anyInt())).willReturn(memberPages);
        given(mapper.membersToMemberDtoResponse(Mockito.anyList())).willReturn(responses);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/members")
                                .param("page","1")
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-members",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].nickName").type(JsonFieldType.STRING).description("작성자"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("data[].password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("data[].about_Me").type(JsonFieldType.STRING).description("자기소개"),
                                        fieldWithPath("data[].url").type(JsonFieldType.STRING).description("url"),

                                        fieldWithPath("pageinfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageinfo.page").type(JsonFieldType.NUMBER).description("페이지"),
                                        fieldWithPath("pageinfo.size").type(JsonFieldType.NUMBER).description("페이지 크기"),
                                        fieldWithPath("pageinfo.totalElements").type(JsonFieldType.NUMBER).description("총 데이터 수"),
                                        fieldWithPath("pageinfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
                                )
                        )
                ));

    }

    @Test
    public void deleteMemberTest() throws Exception {

        long memberId = 1L;
        doNothing().when(memberService).deleteMember(memberId);

        ResultActions actions =
                mockMvc.perform(RestDocumentationRequestBuilders.delete("/members/{member-id}", memberId));

        actions
                .andExpect(status().isNoContent())
                .andDo(document("delete-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 식별자")
                        )

                ));
    }

}


