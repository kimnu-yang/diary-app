package org.diary.db.diary;

import org.diary.db.user.enums.DiaryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    // 사용가 작성한 모든 일기
    // select * from diary where user_id = ? and status = ? order by id desc
    List<DiaryEntity> findAllByUserIdAndStatusOrderByIdDesc(Long id, DiaryStatus status);

    // 특정한 상태인 특정 일기 출력
    // select * from diary where id = ? and status = ?
    Optional<DiaryEntity> findFirstByUserIdAndIdAndStatus(Long userId,Long id, DiaryStatus status);
}
