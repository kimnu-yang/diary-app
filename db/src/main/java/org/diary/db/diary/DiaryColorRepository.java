package org.diary.db.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryColorRepository extends JpaRepository<DiaryColorEntity, Long> {
    List<DiaryColorEntity> findByUserIdAndDiaryId(Long userId, Long diaryId);

    void deleteByUserIdAndDiaryId(Long userId, Long diaryId);
}
