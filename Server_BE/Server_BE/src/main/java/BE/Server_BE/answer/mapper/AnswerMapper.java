package BE.Server_BE.answer.mapper;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostToAnswer (AnswerDto.Post requestBody);
    Answer answerPatchToAnswer (AnswerDto.Patch requestBody);



    default AnswerDto.Response answerToAnswerResponse(Answer answer){
        final String url = "http://localhost:8080/answers/";
        AnswerDto.Response response =AnswerDto.Response
                .builder()
                .answerId(answer.getAnswerId())
                .title(answer.getTitle())
                .body(answer.getBody())
                .boardId(answer.getBoard().getBoardId())
                .memberId(answer.getMember().getMemberId())
                .like(answer.getVote())
                .url(url+answer.getAnswerId())
                .build();
        return response;
    }
    default List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers){
        final String url = "http://localhost:8080/answers/";
        return answers
                .stream()
                .map(answer -> AnswerDto.Response
                        .builder()
                        .answerId(answer.getAnswerId())
                        .title(answer.getTitle())
                        .body(answer.getBody())
                        .memberId(answer.getMember().getMemberId())
                        .boardId(answer.getBoard().getBoardId())
                        .like(answer.getVote())
                        .url(url+answer.getAnswerId())
                        .build())
                .collect(Collectors.toList());
    }
}
