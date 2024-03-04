package org.diary.db.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    List<DiaryEntity> findByUserIdOrderById(Long userId);

    @Query(value = "SELECT id FROM diary WHERE user_id = :userId AND registered_at LIKE :registeredAt%", nativeQuery = true)
    Long findIdByUserIdAndRegisteredAtStartsWith(Long userId, String registeredAt);
}
