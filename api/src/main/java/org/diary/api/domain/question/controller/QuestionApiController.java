package org.diary.api.domain.question.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.TokenUser;
import org.diary.api.common.api.Api;
import org.diary.api.domain.question.business.QuestionBusiness;
import org.diary.api.domain.question.controller.model.QuestionResponse;
import org.diary.db.user.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionApiController {

    private final QuestionBusiness questionBusiness;

    /**
     * 세션에 있는 유저의 최근 접속일 이후 등록된 질문들을 조회한다.
     * @param user
     * @return
     */
    @GetMapping("/")
    public Api<List<QuestionResponse>> questionList(
            @Parameter(hidden = true)
            @TokenUser
            UserEntity user
    ){
        return Api.OK(questionBusiness.questionList(user));
    }
}
