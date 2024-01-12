package org.diary.db.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    BookmarkEntity findFirstByUserIdAndDiaryId(Long id, Long diaryId);
}
