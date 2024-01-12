package org.diary.api.domain.diary.service;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.db.diary.DiaryEntity;
import org.diary.db.diary.DiaryRepository;
import org.diary.db.user.UserEntity;
import org.diary.db.user.enums.DiaryStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    /**
     * 저장상태의 특정 유저의 모든 일기를 조회
     * @param userId - 조회할 유저의 ID 값
     * @return List<DiaryEntity> - 일기 데이터 리스트
     */
    public List<DiaryEntity> getDiaryList(
            Long userId
    ){
        return diaryRepository.findAllByUserIdAndStatusOrderByIdDesc(userId,DiaryStatus.Registered);
    }

    /**
     * 저장상태의 일기 내용을 조회
     *
     * @param diaryId - 조회할 일기의 ID 값
     * @return DiaryEntity - 조회할 일기 데이터
     */
    public DiaryEntity getDiaryDetail(
            UserEntity user,
            Long diaryId
    ){
        return diaryRepository.findFirstByUserIdAndIdAndStatus(user.getId(), diaryId, DiaryStatus.Registered)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 일기를 저장
     *
     * @param diaryEntity - 저장할 일기 데이터
     * @return diaryEntity - 저장된 일기 데이터
     */
    public DiaryEntity register(DiaryEntity diaryEntity) {
        return Optional.ofNullable(diaryEntity)
                .map(it -> {
                    diaryEntity.setStatus(DiaryStatus.Registered);
                    diaryEntity.setCreateAt(LocalDateTime.now());
                    return diaryRepository.save(diaryEntity);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 일기를 수정
     *
     * @param diaryEntity - 수정할 일기 데이터
     * @return diaryEntity - 수정된 일기 데이터
     */
    public DiaryEntity update(DiaryEntity diaryEntity) {
        return Optional.ofNullable(diaryEntity)
                .map(it -> {
                    diaryEntity.setUpdatedAt(LocalDateTime.now());
                    return diaryRepository.save(diaryEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    /**
     * 일기를 삭제 처리
     *
     * @param diaryEntity - 삭제할 일기 데이터
     * @return diaryEntity - 삭제된 일기 데이터
     */
    public DiaryEntity delete(DiaryEntity diaryEntity) {
        return Optional.ofNullable(diaryEntity)
                .map(it -> {
                    diaryEntity.setDeletedAt(LocalDateTime.now());
                    diaryEntity.setStatus(DiaryStatus.Deleted);
                    return diaryRepository.save(diaryEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }


}
