package org.diary.api.domain.setting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.diary.api.common.UserCommon;
import org.diary.api.common.config.TestMvcConfig;
import org.diary.api.domain.setting.controller.model.SettingRegisterRequest;
import org.diary.api.domain.setting.fixture.SettingEntityFixture;
import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.db.setting.SettingEntity;
import org.diary.db.setting.SettingRepository;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SettingControllerTest extends TestMvcConfig {

    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenBusiness tokenBusiness;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity user;
    private String token;

    private static final String BASE_URL = "/api/setting";

    // 설정 관련 동작
    // 1. 설정 가져오기 (한번에)
    // 2. 설정 저장 (한번에)
    // 기본 키값: userId, name 값으로 구분
    // 저장하는 경우 있으면 update 없는 경우 insert
    // 탈퇴 처리는 설정 삭제하는 경우는 유저 DELETE 하는 경우


    void setUser() {
        UserCommon common = new UserCommon(userRepository, tokenBusiness);
        user = common.user;
        token = common.token;
    }

    @Test
    void saveSettings() throws JsonProcessingException {
        // 1. 설정 가져오기
        // 유저 생성
        setUser();

        // given
        // userId, settingList
        SettingRegisterRequest request = SettingEntityFixture.anSettingFixture().settingRegisterRequestBuild(user.getId());

        // when
        String result = doPostWithToken(BASE_URL, request, token);

        // then
        JsonNode resultJson = objectMapper.readTree(result);
//        Assert.notNull(resultJson.get("body").get("id"), "질문 조회 실패");
        System.out.println(resultJson.get("body"));
    }

    @Test
    void getSettings() throws JsonProcessingException {
        // 유저 생성
        setUser();

        // given
        // userId, settingList
        List<SettingEntity> settings = SettingEntityFixture.anSettingFixture().settingFixtureUserId(user.getId()).settingsEntityBuild();
        settingRepository.saveAll(settings);

        // when
        String result = doGetWithToken(BASE_URL, token);

        // then
        JsonNode resultJson = objectMapper.readTree(result);
//        Assert.notNull(resultJson.get("body").get("id"), "질문 조회 실패");
        System.out.println(resultJson.get("body"));
    }


}
