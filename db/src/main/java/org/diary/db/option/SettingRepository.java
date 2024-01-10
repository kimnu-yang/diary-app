package org.diary.db.option;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SettingRepository extends JpaRepository<SettingEntity, Long> {

    // select * from setting where name = ? limit 1
    Optional<SettingEntity> findFirstByUserIdAndName(Long userId, String name);

    // select * from setting where user_id = ?
    List<SettingEntity> findByUserId(Long userId);


    Long countByUserIdAndName(Long userId, String name);
}