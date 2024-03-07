package org.diary.db.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryTagRepository extends JpaRepository<DiaryTagEntity, Long> {
    List<DiaryTagEntity> findByUserIdAndDiaryId(Long userId, Long diaryId);

    void deleteByUserIdAndDiaryId(Long userId, Long diaryId);

    void deleteAllByUserId(Long userId);
}
