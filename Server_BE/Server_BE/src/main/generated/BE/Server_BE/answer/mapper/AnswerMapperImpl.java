package BE.Server_BE.answer.mapper;

import BE.Server_BE.answer.dto.AnswerDto.Patch;
import BE.Server_BE.answer.dto.AnswerDto.Post;
import BE.Server_BE.answer.entity.Answer;
import BE.Server_BE.answer.entity.Answer.AnswerBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-24T11:15:36+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostToAnswer(Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        AnswerBuilder answer = Answer.builder();

        answer.title( requestBody.getTitle() );
        answer.body( requestBody.getBody() );

        return answer.build();
    }

    @Override
    public Answer answerPatchToAnswer(Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        AnswerBuilder answer = Answer.builder();

        answer.answerId( requestBody.getAnswerId() );
        answer.title( requestBody.getTitle() );
        answer.body( requestBody.getBody() );

        return answer.build();
    }
}
