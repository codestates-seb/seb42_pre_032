package BE.Server_BE.member.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;

public class MemberDto {
    @Getter
    public static class Post{
        String nickName;
        String email;
        String password;
        String about_Me;

    }
    @Getter
    @Setter
    public static class Patch{
        long memberId;
        String nickName;
        String email;
        String password;
        String about_Me;
    }
    @Getter
    @Setter
    public static class Response{
        long memberId;
        String nickName;
        String email;
        String password;
        String about_Me;
        String url;
    }
}
