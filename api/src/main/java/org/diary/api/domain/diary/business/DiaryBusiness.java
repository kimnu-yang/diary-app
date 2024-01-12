package org.diary.api.domain.diary.business;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Business;
import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.controller.model.DiaryResponse;
import org.diary.api.domain.diary.controller.model.DiaryUpdateRequest;
import org.diary.api.domain.diary.converter.DiaryConverter;
import org.diary.api.domain.diary.service.DiaryService;
import org.diary.db.diary.DiaryRepository;
import org.diary.db.user.UserEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class DiaryBusiness {

    private final DiaryService diaryService;
    private final DiaryConverter diaryConverter;

    /**
     * 특정 유저가 작성한 모든 일기를 조회
     *
     * @param user
     * @return List<DiaryResponse>
     */
    public List<DiaryResponse> diaryList(
            UserEntity user
    ){
        var list = diaryService.getDiaryList(user.getId());

        return list.stream()
                .map(diaryConverter::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * 특정 유저가 작성한 일기의 내용을 조회
     *
     * @param user
     * @param diaryId
     * @return
     */
    public DiaryResponse diaryDetail(
            UserEntity user,
            Long diaryId
    ){
        return diaryConverter.toResponse(diaryService.getDiaryDetail(user, diaryId));
    }

    /**
     * 일기를 등록
     *
     * @param request
     * @return DiaryResponse
     */
    public DiaryResponse register(
            UserEntity user,
            DiaryRegisterRequest request
    ){
        var entity = diaryConverter.toEntity(user,request);
        var newEntitiy = diaryService.register(entity);
        return diaryConverter.toResponse(newEntitiy);
    }

    /**
     * 일기를 수정
     *
     * @param request
     * @return DiaryResponse
     */
    public DiaryResponse update(
            UserEntity user,
            Long diaryId,
            DiaryUpdateRequest request
    ){
        var loadEntity = diaryService.getDiaryDetail(user,diaryId);
        var entity = diaryConverter.applyUpdate(loadEntity, request);
        var updatedEntity = diaryService.update(entity);

        return diaryConverter.toResponse(updatedEntity);
    }

    /**
     * 일기를 삭제
     *
     * @param diaryId
     * @return DiaryResponse
     */
    public DiaryResponse delete(
            UserEntity user,
            Long diaryId
    ){
        var entity = diaryService.getDiaryDetail(user,diaryId);
        var deletedEntity = diaryService.delete(entity);
        return diaryConverter.toResponse(deletedEntity);
    }
}
