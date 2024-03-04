package org.diary.db.diary;

import org.diary.db.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    List<DiaryEntity> findByUserIdOrderById(Long userId);

    @Query(value = "SELECT * FROM diary WHERE user_id = :userId AND (registered_at >= :lastSyncTime OR updated_at >= :lastSyncTime OR deleted_at >= :lastSyncTime) ORDER BY id DESC", nativeQuery = true)
    List<DiaryEntity> findByUserIdAndCheckLastSyncTimeOrderById(Long userId, LocalDateTime lastSyncTime);

    @Query(value = "SELECT id FROM diary WHERE user_id = :userId AND registered_at LIKE :registeredAt%", nativeQuery = true)
    Long findIdByUserIdAndRegisteredAtStartsWith(Long userId, String registeredAt);

    @Query(value = "SELECT * FROM diary WHERE user_id = :userId AND registered_at LIKE :registeredAt%", nativeQuery = true)
    DiaryEntity findByUserIdAndRegisteredAtStartsWith(Long userId, String registeredAt);
}
