package BE.Server_BE.member.mapper;


import BE.Server_BE.member.dto.MemberDto;
import BE.Server_BE.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberDtoPostToMember(MemberDto.Post post);
    Member memberDtoPatchToMember(MemberDto.Patch patch);
    MemberDto.Response memberToMemberDtoResponse (Member member);
    List<MemberDto.Response> membersToMemberDtoResponse (List<Member> members);

}
