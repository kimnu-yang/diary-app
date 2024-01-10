package org.diary.api.domain.setting.fixture;

import org.diary.api.domain.setting.controller.model.SettingRegisterRequest;
import org.diary.db.setting.SettingEntity;

import java.util.ArrayList;
import java.util.List;

public class SettingEntityFixture {

    private Long userId;
    private String name = "setting_key";
    private final String value = "setting_value";
    private String name2 = "setting_key2";
    private final String value2 = "setting_value2";
    private String name3 = "setting_key3";
    private final String value3 = "setting_value3";
    private String name4 = "setting_key3";
    private final String value4 = "setting_update_value4";

    public static SettingEntityFixture anSettingFixture() {
        return new SettingEntityFixture();
    }


    public SettingEntityFixture settingFixtureName(String name) {
        this.name = name;
        return this;
    }

    public SettingEntityFixture settingFixtureUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public SettingEntity settingEntityBuild() {
        return new SettingEntity(
                userId,
                name,
                value
        );
    }

    /**
     * 설정값 3개 저장하기 위한 함수
     * 직접 save 하기 때문에 userId 세팅 포함
     *
     * @return 설정값 목록
     */
    public List<SettingEntity> settingsEntityBuild() {
        List<SettingEntity> settings = new ArrayList<>();
        settings.add(SettingEntity.builder().userId(userId).name(name).value(value).build());
        settings.add(SettingEntity.builder().userId(userId).name(name2).value(value2).build());
        settings.add(SettingEntity.builder().userId(userId).name(name3).value(value3).build());
        return settings;
    }

    /**
     * 설정값 3개 저장하기 위한 함수
     * doPost 하기 위한 함수
     *
     * @return 설정값 목록
     */
    public List<SettingRegisterRequest> settingRegisterRequestBuild() {
        List<SettingRegisterRequest> settings = new ArrayList<>();

        settings.add(SettingRegisterRequest.builder().name(name).value(value).build());
        settings.add(SettingRegisterRequest.builder().name(name2).value(value2).build());
        settings.add(SettingRegisterRequest.builder().name(name3).value(value3).build());
        settings.add(SettingRegisterRequest.builder().name(name4).value(value4).build());

        return settings;
    }

}
