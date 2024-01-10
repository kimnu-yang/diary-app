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

    public List<SettingEntity> settingsEntityBuild() {
        List<SettingEntity> settings = new ArrayList<>();
        settings.add(SettingEntity.builder().userId(userId).name(name).value(value).build());
        settings.add(SettingEntity.builder().userId(userId).name(name2).value(value2).build());
        settings.add(SettingEntity.builder().userId(userId).name(name3).value(value3).build());
        return settings;
    }

    public SettingRegisterRequest settingRegisterRequestBuild(Long userId) {
        List<SettingRegisterRequest.SettingObj> settings = new ArrayList<>();

        settings.add(SettingRegisterRequest.SettingObj.builder().name(name).value(value).build());
        settings.add(SettingRegisterRequest.SettingObj.builder().name(name2).value(value2).build());
        settings.add(SettingRegisterRequest.SettingObj.builder().name(name3).value(value3).build());

        return SettingRegisterRequest.builder().userId(userId).settings(settings).build();
    }

}
