package BE.Server_BE.answer.dto;

import BE.Server_BE.comment.dto.CommentDto;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class AnswerDto {
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Post{
        @NotBlank
        private String title;
        @NotBlank
        private String body;

    }

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Patch{
        private long answerId;
        private String title;
        private String body;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long answerId;
        private long memberId;
        private long boardId;
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;
        @Column(name = "LAST_MODIFIED_AT")
        private LocalDateTime modifiedAt;
        private String title;
        private String body;
        private long like;
        private String url;
        private List<CommentDto.Response> comments;
    }
}
