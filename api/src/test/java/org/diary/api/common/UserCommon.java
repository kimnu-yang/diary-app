package org.diary.api.common;

import org.diary.api.domain.token.business.TokenBusiness;
import org.diary.api.domain.user.fixture.UserEntityFixture;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.diary.db.user.enums.UserStatus;

public class UserCommon {

    public Long userId;
    public String token;

    // 회원가입
    UserEntity user = UserEntityFixture.anUserFixture().userKakaoEntityBuild();

    public UserCommon(UserRepository userRepository, TokenBusiness tokenBusiness) {
        userId = userRepository.save(user).getId();

        // 토큰 발급
        token = tokenBusiness.issueToken(UserEntity.builder()
                .id(userId)
                .status(UserStatus.REGISTERED)
                .build()).getAccessToken();
    }
}
