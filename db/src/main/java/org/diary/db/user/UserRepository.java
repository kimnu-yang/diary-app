package org.diary.db.user;

import org.diary.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // select * from user where id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    // select * from user where id = ? and kakao_user_id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdAndKakaoUserIdAndStatusOrderByIdDesc(Long id, Long kakaoUserId, UserStatus status);

    // select * from user where id = ? and google_user_id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdAndGoogleUserIdAndStatusOrderByIdDesc(Long id, Long googleUserId, UserStatus status);
}