package BE.Server_BE.comment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CommentDto {
    @Getter
    public static class Post {
        String body;
        long answerId;
        // long memberId; 필요하지만 추후 UserDetails로 가져올 예정
    }
    @Getter
    @Setter
    public static class Patch {
        long commentId;
        String body;
    }
    @Getter
    @Setter
    @Builder
    public static class Response {
        long commentId;
        String body;
        String url;
//        long memberId; // 추후...
        long answerId;

    }
}
