package BE.Server_BE.comment.dto;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {
    @Getter
    @Setter
    @AllArgsConstructor // test
    @NoArgsConstructor // test
    public static class Post {
        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        String body;

    }
    @Getter
    @Setter
    @AllArgsConstructor // test
    @NoArgsConstructor // test
    public static class Patch {
        String body;
    }
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor // test
    @NoArgsConstructor
    public static class Response {
        long commentId;
        long answerId;
        long memberId;
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;
        @Column(name = "LAST_MODIFIED_AT")
        private LocalDateTime modifiedAt;
        String body;
        String url;
    }
}
