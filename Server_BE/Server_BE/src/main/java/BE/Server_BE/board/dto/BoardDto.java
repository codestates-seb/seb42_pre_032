package BE.Server_BE.board.dto;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class BoardDto {
    @Getter
    @AllArgsConstructor
<<<<<<< Updated upstream
    @NoArgsConstructor //test

=======
    @NoArgsConstructor
>>>>>>> Stashed changes
    public static class Post {
        @NotBlank
        private String title;
        @NotBlank
        private String body;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    public static class Patch {
        private long memberId;
        private long boardId;
        private String title;
        private String body;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private long boardId;
        private long memberId;
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;
        @Column(name = "LAST_MODIFIED_AT")
        private LocalDateTime modifiedAt;
        private String writer;
        private String title;
        private String body;
        private long like;
        private String url;
        private List<AnswerDto.Response> answers;
    }
}
