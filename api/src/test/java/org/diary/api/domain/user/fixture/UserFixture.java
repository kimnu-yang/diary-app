package org.diary.api.domain.user.fixture;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.diary.api.domain.user.controller.model.UserLoginRequest;
import org.diary.api.domain.user.controller.model.UserRegisterRequest;
import org.diary.db.user.UserEntity;

public class UserFixture {

    private String address = "test addr";
    private String email = "testemail@test.com";
    private String name = "테스트";
    private String password = "1234";

    public static UserFixture anUserFixture() {
        return new UserFixture();
    }

    public UserFixture userFixtureAddress(final String address) {
        this.address = address;
        return this;
    }

    public UserFixture userFixtureEmail(final String email) {
        this.email = email;
        return this;
    }

    public UserFixture userFixtureName(final String name) {
        this.name = name;
        return this;
    }

    public UserFixture userFixturePassword(final String password) {
        this.password = password;
        return this;
    }

    public UserLoginRequest loginRequestBuild() {
        return new UserLoginRequest(
                email,
                password
        );
    }

    public UserRegisterRequest RegisterRequestBuild() {
        return new UserRegisterRequest(
                name,
                email,
                address,
                password
        );
    }
}
