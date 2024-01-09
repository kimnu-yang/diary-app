package org.diary.api.domain.user.fixture;

import org.diary.db.user.UserEntity;
import org.diary.db.user.enums.UserStatus;
import org.diary.db.user.enums.UserType;

import java.time.LocalDateTime;

public class UserEntityFixture {

    private Long id;

    private UserStatus status = UserStatus.REGISTERED;

    private UserType type = UserType.Kakao;

    private LocalDateTime registeredAt = LocalDateTime.now();

    private LocalDateTime unregisteredAt = null;

    private LocalDateTime lastLoginAt = LocalDateTime.now();

    public static UserEntityFixture anUserFixture() {
        return new UserEntityFixture();
    }


    public UserEntityFixture userFixtureId(final Long id) {
        this.id = id;
        return this;
    }


    public UserEntity userEntityBuild() {
        return UserEntity.builder().type(type).status(status).registeredAt(registeredAt).lastLoginAt(lastLoginAt).unregisteredAt(unregisteredAt).build();
    }


}
