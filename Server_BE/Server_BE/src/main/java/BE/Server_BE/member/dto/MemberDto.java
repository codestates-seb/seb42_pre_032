package BE.Server_BE.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberDto {
    @Getter
    @NoArgsConstructor
    @Setter
    @AllArgsConstructor
    public static class Post{
        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        String nickName;
        @NotBlank
        @Email
        String email;
        @NotBlank
        String password;
        String about_Me;
    }
    @Getter
    @Setter
    @AllArgsConstructor // test
    @NoArgsConstructor //test
    public static class Patch{
        long memberId;
        String nickName;
        String email;
        String password;
        String about_Me;
    }
    @Getter
    @Setter
    @AllArgsConstructor //test
    @NoArgsConstructor //test
    public static class Response{
        long memberId;
        String nickName;
        String email;
        String password;
        String about_Me;
        String url;
    }
}
