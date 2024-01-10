package org.diary.api.domain.setting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.lang.Assert;
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

    void setUser() {
        UserCommon common = new UserCommon(userRepository, tokenBusiness);
        user = common.user;
        token = common.token;
    }

    @Test
    void saveSettings() throws JsonProcessingException {
        // 유저 생성
        setUser();

        // given
        // userId, settingList
        SettingRegisterRequest request = SettingEntityFixture.anSettingFixture().settingRegisterRequestBuild(user.getId());

        // when
        String result = doPostWithToken(BASE_URL, request, token);

        // then
        JsonNode resultJson = objectMapper.readTree(result);
        Assert.state(resultJson.get("body").size() >= 3, "설정 저장 실패");
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
        Assert.state(resultJson.get("body").size() >= 3, "설정 조회 실패");
    }

}
