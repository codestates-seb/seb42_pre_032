package BE.Server_BE.answer.mapper;

import BE.Server_BE.answer.dto.AnswerDto;
import BE.Server_BE.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostToAnswer (AnswerDto.Post requestBody);
    Answer answerPatchToAnswer (AnswerDto.Patch requestBody);
    AnswerDto.Response answerToAnswerResponse (Answer answer);
    List<AnswerDto.Response> answersToAnswerResponses (List<Answer> answers);

}
