package BE.Server_BE.member.mapper;

import BE.Server_BE.member.dto.MemberDto.Patch;
import BE.Server_BE.member.dto.MemberDto.Post;
import BE.Server_BE.member.dto.MemberDto.Response;
import BE.Server_BE.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-22T17:17:43+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberDtoPostToMember(Post post) {
        if ( post == null ) {
            return null;
        }

        Member member = new Member();

        member.setNickName( post.getNickName() );
        member.setEmail( post.getEmail() );
        member.setPassword( post.getPassword() );
        member.setAbout_Me( post.getAbout_Me() );

        return member;
    }

    @Override
    public Member memberDtoPatchToMember(Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( patch.getMemberId() );
        member.setNickName( patch.getNickName() );
        member.setEmail( patch.getEmail() );
        member.setPassword( patch.getPassword() );
        member.setAbout_Me( patch.getAbout_Me() );

        return member;
    }

    @Override
    public Response memberToMemberDtoResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String nickName = null;
        String email = null;
        String password = null;
        String about_Me = null;

        memberId = member.getMemberId();
        nickName = member.getNickName();
        email = member.getEmail();
        password = member.getPassword();
        about_Me = member.getAbout_Me();

        String url = null;

        Response response = new Response( memberId, nickName, email, password, about_Me, url );

        return response;
    }

    @Override
    public List<Response> membersToMemberDtoResponse(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberDtoResponse( member ) );
        }

        return list;
    }
}
