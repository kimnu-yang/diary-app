package org.diary.api.domain.option.fixture;

import org.diary.db.option.SettingEntity;

public class SettingEntityFixture {

    private Long userId;
    private String name = "option_key";
    private final String value = "option_value";

    public static SettingEntityFixture anOptionFixture() {
        return new SettingEntityFixture();
    }


    public SettingEntityFixture optionFixtureName(String name) {
        this.name = name;
        return this;
    }

    public SettingEntityFixture optionFixtureUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public SettingEntity optionEntityBuild() {
        return new SettingEntity(
                userId,
                name,
                value
        );
    }
}
