package org.diary.api.domain.setting.fixture;

import org.diary.db.setting.SettingEntity;

public class SettingEntityFixture {

    private Long userId;
    private String name = "setting_key";
    private final String value = "setting_value";

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
}
