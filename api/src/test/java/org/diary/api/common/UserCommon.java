package org.diary.api.common;

import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.api.domain.user.controller.model.UserRegisterRequest;
import org.diary.api.domain.user.fixture.UserFixture;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.diary.db.user.enums.UserStatus;

public class UserCommon {

    public Long user_id;
    public String token;

    // 회원가입
    UserRegisterRequest userRegisterRequest = UserFixture.anUserFixture().registerRequestBuild();

    public UserCommon(UserRepository userRepository, TokenBusiness tokenBusiness){
        user_id = userRepository.save(UserEntity.builder()
                .name(userRegisterRequest.getName())
                .email(userRegisterRequest.getEmail())
                .address(userRegisterRequest.getAddress())
                .password(userRegisterRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .build()
        ).getId();

        // 토큰 발급
        token = tokenBusiness.issueToken(UserEntity.builder()
                .id(user_id)
                .status(UserStatus.REGISTERED)
                .build()).getAccessToken();
    }
}
