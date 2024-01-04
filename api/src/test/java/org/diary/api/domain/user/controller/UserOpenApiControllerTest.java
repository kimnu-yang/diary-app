package org.diary.api.domain.user.controller;

import io.jsonwebtoken.lang.Assert;
import org.diary.api.domain.user.common.TestConfig;
import org.diary.api.domain.user.service.UserService;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.diary.db.user.enums.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserOpenApiControllerTest extends TestConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void register() {
        // given
        UserEntity user = UserEntity.builder().id(1L).name("테스트").password("1234").email("email@gamil.com").address("테스트 주소").status(UserStatus.REGISTERED).build();

        // when
        Object obj = userService.register(user);

        // then
        Assert.notNull(obj, "저장 실패");
    }

    @Test
    void login() {
        System.out.println("1234");
    }
}