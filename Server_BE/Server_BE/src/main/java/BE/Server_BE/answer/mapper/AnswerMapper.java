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
//    AnswerDto.Response answerToAnswerResponse(Answer answer);

    default AnswerDto.Response answerToAnswerResponse(Answer answer){
        AnswerDto.Response response =AnswerDto.Response
                .builder()
                .answerId(answer.getAnswerId())
                .title(answer.getTitle())
                .body(answer.getBody())
                .boardId(answer.getBoard().getBoardId())
//                .memberId(answer.getMember().getMemberId())
                .build();

        return response;
    }
    default List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers){
        return answers
                .stream()
                .map(answer -> AnswerDto.Response
                        .builder()
                        .answerId(answer.getAnswerId())
                        .title(answer.getTitle())
                        .body(answer.getBody())
//                        .memberId(answer.getMember().getMemberId())
                        .boardId(answer.getBoard().getBoardId())
                        .build())
                .collect(Collectors.toList());
    }
}
