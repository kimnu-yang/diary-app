package org.diary.api.domain.diary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.fixture.DiaryFixture;
import org.diary.api.domain.user.controller.model.UserLoginRequest;
import org.diary.api.domain.user.controller.model.UserRegisterRequest;
import org.diary.api.domain.user.fixture.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class DiaryApiControllerTest extends TestMvcConfig {

    private static final String USER_URL = "/open-api/user";
    private static final String DIARY_URL = "/api/diary";

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addDiary() throws JsonProcessingException {

        // given(값 설정)

        // - 회원가입 프로세스
        UserRegisterRequest userRegisterRequest = UserFixture.anUserFixture().registerRequestBuild();
        String userRegisterResult = doPost(USER_URL + "/register", userRegisterRequest);
        JsonNode userRegisterResultJson = objectMapper.readTree(userRegisterResult);
        Assert.notNull(userRegisterResultJson.get("body").get("id"), "일기 추가 실패");

        // - 로그인 프로세스
        UserLoginRequest userLoginRequest = UserFixture.anUserFixture().loginRequestBuild();
        String userLoginResult = doPost(USER_URL + "/login", userLoginRequest);
        JsonNode userLoginResultJson = objectMapper.readTree(userLoginResult);
        Assert.notNull(userLoginResultJson.get("body").get("access_token"),"로그인 실패");
        String token = String.valueOf(userLoginResultJson.get("body").get("access_token"));

        // - 다이어리 작성 값
        DiaryRegisterRequest diaryRegisterRequest = DiaryFixture.anUserFixture().diaryRegisterRequest();

        //when(실행)
        String result = doPostWithToken(DIARY_URL + "/register", diaryRegisterRequest, token);

        //then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "일기 추가 실패");
    }
}
