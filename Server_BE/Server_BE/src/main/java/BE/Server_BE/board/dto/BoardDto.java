package BE.Server_BE.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class BoardDto {
    @Getter
    public static class Post {
        @NotNull
        private long boardId;
        @NotBlank
        private String title;
        @NotBlank
        private String body;
    }

    @Getter
    @Setter
    public static class Patch {
        @NotNull
        private long boardId;
        private String title;
        private String body;

    }

    @Getter
    @Setter
    public static class Response {
        private long boardId;
        private String writer;
        private String title;
        private String body;
    }


}
