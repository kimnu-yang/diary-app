package org.diary.api.domain.question.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.diary.api.common.UserCommon;
import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.db.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class QuestionApiControllerTest extends TestMvcConfig {

    private static final String BASE_URL = "/api/question";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenBusiness tokenBusiness;

    @Test
    void getUpdatedQuestion() throws JsonProcessingException {

        // given(값 설정)
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);

        // when(실행)
        String result = doGetWithToken(BASE_URL,userCommon.token);

        // then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "질문 조회 실패");
    }
}
