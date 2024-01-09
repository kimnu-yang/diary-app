package org.diary.api.domain.diary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.diary.api.common.DiaryCommon;
import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.common.UserCommon;
import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.controller.model.DiaryUpdateRequest;
import org.diary.api.domain.diary.fixture.DiaryFixture;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.db.diary.DiaryRepository;
import org.diary.db.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class DiaryApiControllerTest extends TestMvcConfig {

    private static final String DIARY_URL = "/api/diary";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private TokenBusiness tokenBusiness;

    @Test
    void addDiary() throws JsonProcessingException {

        // given(값 설정)

        // - User 정보
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);

        // - 다이어리 작성 값
        DiaryRegisterRequest diaryRegisterRequest = DiaryFixture.anUserFixture().diaryRegisterRequest();

        //when(실행)
        String result = doPostWithToken(DIARY_URL + "/register", diaryRegisterRequest, userCommon.token);

        //then(검증)
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.notNull(resultJson.get("body").get("id"), "일기 추가 실패");
    }

    @Test
    void updateDiary() throws JsonProcessingException {

        //given(값 설정)

        // - 유지 정보
        UserCommon userCommon = new UserCommon(userRepository, tokenBusiness);
        DiaryCommon diaryCommon = new DiaryCommon(diaryRepository, userCommon.user_id);

        // - 다이어리 추가
        Long diary_id = diaryCommon.diary_id;

        // - 다이어리 수정 값
        DiaryUpdateRequest diaryUpdateRequest = DiaryFixture.anUserFixture().diaryUpdateRequest();


        //when(실행)
        String diaryUpdateResultJson = doPostWithToken(DIARY_URL + "/update/" + diary_id, diaryUpdateRequest, userCommon.token);

        //then(검증)
        JsonNode updateResultJson = objectMapper.readTree(diaryUpdateResultJson);
        Assert.notNull(updateResultJson.get("body").get("id"), "일기 수정 실패");
    }
}
