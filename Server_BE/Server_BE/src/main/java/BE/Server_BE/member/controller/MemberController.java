package BE.Server_BE.member.controller;


import BE.Server_BE.member.dto.MemberDto;
import BE.Server_BE.member.entity.Member;
import BE.Server_BE.member.mapper.MemberMapper;
import BE.Server_BE.member.response.MultiResponse;
import BE.Server_BE.member.response.PageInfo;
import BE.Server_BE.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/members")
public class MemberController {

    private final String url = "http://localhost:8080/members/";

    MemberMapper mapper;
    MemberService memberService;

    public MemberController(MemberMapper mapper, MemberService memberService) {
        this.mapper = mapper;
        this.memberService = memberService;
    }

    @PostMapping
    ResponseEntity postMember(@RequestBody @Valid MemberDto.Post post) throws Exception {
        Member member = mapper.memberDtoPostToMember(post);
        Member createdMember = memberService.createMember(member);
        MemberDto.Response response = mapper.memberToMemberDtoResponse(createdMember);
        response.setUrl(url + response.getMemberId());
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                               @RequestBody @Valid MemberDto.Patch patch) throws Exception {
        Member member = mapper.memberDtoPatchToMember(patch);
        member.setMemberId(memberId);
        Member updatedMember = memberService.updateMember(member);
        MemberDto.Response response = mapper.memberToMemberDtoResponse(updatedMember);
        response.setUrl(url + response.getMemberId());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){
        Member findMember = memberService.getMember(memberId);
        MemberDto.Response response = mapper.memberToMemberDtoResponse(findMember);
        response.setUrl(url+memberId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity getMembers(@Positive @RequestParam int page, @Positive @RequestParam int size) throws Exception{
        Page<Member> memberPage = memberService.getMembers(page-1, size);
        PageInfo pageInfo = new PageInfo(memberPage.getNumber(), memberPage.getSize(),
                memberPage.getTotalElements(),memberPage.getTotalPages());
        List<Member> members = memberPage.getContent();
        List<MemberDto.Response> responses = mapper.membersToMemberDtoResponse(members);
        responses.stream().forEach(s -> s.setUrl(url+s.getMemberId()));

        return new ResponseEntity<>(
                new MultiResponse<>(responses, pageInfo), HttpStatus.OK
        );
    }
    @DeleteMapping("/{member-id}")
    ResponseEntity deleteMember (@Positive @PathVariable("member-id") long memberId) throws Exception{
        memberService.deleteMember(memberId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
