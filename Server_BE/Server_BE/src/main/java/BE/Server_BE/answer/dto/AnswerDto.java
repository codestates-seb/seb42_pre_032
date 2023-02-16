package BE.Server_BE.answer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AnswerDto {

    @Getter
    public static class Post{

        @NotBlank(message = "제목은 공백이 아니어야 합니다")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다")
        private String body;
    }

    @Getter
    public static class Patch{

        @NotNull
        private long answerId;

        private String title;

        private String body;

    }

    @Getter
    @Setter
    public static class Response{

        private Long answerId;

        private String title;

        private String writer;

        private String body;

        private Long boardId;

//    private List<CommentDto.Response> comments;

//        private Long like;
    }

}
