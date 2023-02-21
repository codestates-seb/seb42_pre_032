package BE.Server_BE.board.dto;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


public class BoardDto {
    @Getter
    public static class Post {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @Getter
    @Setter
    public static class Patch {
        private long memberId;
        @NotNull
        private long boardId;
        private String title;
        private String body;

    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private long boardId;
        private String writer;
        private String title;
        private String body;
        private long like;
        private List<AnswerDto.Response> answers;
        private String url;
    }


}
