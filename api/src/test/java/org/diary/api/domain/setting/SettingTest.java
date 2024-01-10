package org.diary.api.domain.setting;

import io.jsonwebtoken.lang.Assert;
import org.diary.api.common.UserCommon;
import org.diary.api.common.config.TestConfig;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.setting.fixture.SettingEntityFixture;
import org.diary.db.setting.SettingEntity;
import org.diary.db.setting.SettingRepository;
import org.diary.db.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SettingTest extends TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Test
    void saveSetting() {
        // given
        // 유저 생성
        UserCommon common = new UserCommon(userRepository);

        SettingEntity entity = SettingEntityFixture.anSettingFixture().settingFixtureUserId(common.userId).settingEntityBuild();

        // when
        // 이미 있는 경우 api 오류
        if (settingRepository.findFirstByUserIdAndName(entity.getUserId(), entity.getName()).isPresent()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, "이미 있는 설정값입니다.");
        }

        // 해당 유저로 세팅값 저장
        SettingEntity result = settingRepository.save(entity);

        // then
        Assert.notNull(result, "저장 오류 발생");
    }


    @Test
    void findUserSetting() {
        // given
        // 유저 생성
        UserCommon common = new UserCommon(userRepository);

        List<SettingEntity> settings = SettingEntityFixture.anSettingFixture().settingFixtureUserId(common.userId).settingsEntityBuild();

        // 해당 유저로 세팅값 저장
        settingRepository.saveAll(settings);

        // when
        // 이미 있는 경우 api 오류
        List<SettingEntity> result = settingRepository.findByUserId(common.userId);

        // then
        Assert.state(result.size() >= 3, "조회 오류 발생");
    }

    @Test
    void deleteSetting() {
        // given
        // 유저 생성
        UserCommon common = new UserCommon(userRepository);

        // when
        SettingEntity entity = SettingEntityFixture.anSettingFixture().settingFixtureUserId(common.userId).settingEntityBuild();

        SettingEntity save = settingRepository.save(entity);

        // 해당 유저로 세팅값 저장
        settingRepository.deleteById(save.getId());

        // then
        Assert.state(settingRepository.findById(save.getId()).isEmpty(), "삭제 오류 발생");
    }

    @Test
    void findAll() {
        System.out.println(userRepository.findAll());
        System.out.println(settingRepository.findAll());
    }
}