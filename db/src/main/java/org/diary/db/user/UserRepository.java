package org.diary.db.user;

import org.diary.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // select * from user where id = ? and status = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    @Query(value = "SELECT u.* FROM user u INNER JOIN kakao_token k ON u.id = k.user_id " +
            "WHERE u.id = :userId AND  k.access_token = :access_token AND u.status = :#{#status.name()} ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<UserEntity> findUserInKakao(Long userId, String access_token, UserStatus status);
}