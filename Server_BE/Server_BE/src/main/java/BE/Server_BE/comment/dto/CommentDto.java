package BE.Server_BE.comment.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CommentDto {
    @Getter
    public static class Post {
        String body;

    }
    @Getter
    @Setter
    public static class Patch {
        String body;
    }
    @Getter
    @Setter
    @Builder
    public static class Response {
        long commentId;
        long answerId;
        long memberId;
        String body;
        String url;
    }
}
