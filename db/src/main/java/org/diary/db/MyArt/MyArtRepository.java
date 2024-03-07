package org.diary.db.MyArt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MyArtRepository extends JpaRepository<MyArtEntity, Long> {

    List<MyArtEntity> findByUserIdOrderById(Long userId);

    @Query(value = "SELECT * FROM my_art WHERE user_id = :userId AND registered_at >= :lastSyncTime ORDER BY id DESC", nativeQuery = true)
    List<MyArtEntity> findByUserIdAndCheckLastSyncTimeOrderById(Long userId, LocalDateTime lastSyncTime);

    @Query(value = "SELECT id FROM my_art WHERE user_id = :userId AND base_date LIKE :baseDate%", nativeQuery = true)
    Long findIdByUserIdAndBaseDateStartsWith(Long userId, String baseDate);
}
