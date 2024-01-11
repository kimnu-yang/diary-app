package org.diary.api.domain.question.business;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Business;
import org.diary.api.domain.question.controller.model.QuestionResponse;
import org.diary.api.domain.question.converter.QuestionConverter;
import org.diary.api.domain.question.service.QuestionService;
import org.diary.db.user.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class QuestionBusiness {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;

    /**
     * 유저의 최근 접속일 이후 등록된 질문을 조회
     * @param user
     * @return
     */
    public List<QuestionResponse> questionList(
            UserEntity user
    ) {
       var list = questionService.getQuestionAfterDay(user.getLastLoginAt());

       return list.stream()
               .map(questionConverter::toResponse)
               .collect(Collectors.toList());
    }
}
