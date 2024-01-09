package org.diary.db.token;

import org.diary.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KakaoTokenRepository  extends JpaRepository<KakaoTokenEntity, Long>, JpaSpecificationExecutor<KakaoTokenEntity> {
    @Query(value = "SELECT * FROM user u INNER JOIN kakao_token k ON u.id = k.user_id " +
            "WHERE u.id = :userId AND  k.access_token = :access_token AND u.status = :#{#status.name()} ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<KakaoTokenEntity> findKakaoToken(Long userId, String access_token, UserStatus status);
}
