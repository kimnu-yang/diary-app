package org.diary.api.domain.diary.converter;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Converter;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.diary.controller.model.DiaryRegisterRequest;
import org.diary.api.domain.diary.controller.model.DiaryResponse;
import org.diary.api.domain.diary.controller.model.DiaryUpdateRequest;
import org.diary.db.diary.DiaryEntity;
import org.diary.db.user.UserEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class DiaryConverter {

    public DiaryEntity toEntity(UserEntity user, DiaryRegisterRequest request) {
            return Optional.ofNullable(request)
                    .map(it -> {
                        return DiaryEntity.builder()
                                .userId(user.getId())
                                .title(request.getTitle())
                                .content(request.getContent())
//                                .location(request.getLocation())
//                                .weather(request.getWeather())
//                                .emotion(request.getEmotion())
//                                .createAt(LocalDateTime.now())
                                .build();
                    })
                    .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public DiaryResponse toResponse(DiaryEntity diaryEntity) {
        return Optional.ofNullable(diaryEntity)
                .map(it -> {
                    return DiaryResponse.builder()
                            .id(diaryEntity.getId())
                            .title(diaryEntity.getTitle())
                            .content(diaryEntity.getContent())
//                            .weather(diaryEntity.getWeather())
//                            .emotion(diaryEntity.getEmotion())
//                            .location(diaryEntity.getLocation())
//                            .status(diaryEntity.getStatus())
//                            .createdAt(diaryEntity.getCreateAt())
                            .updatedAt(diaryEntity.getUpdatedAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public DiaryEntity applyUpdate(DiaryEntity entity, DiaryUpdateRequest request) {

        if (request != null && request.getTitle() != null) entity.setTitle(request.getTitle());
        if (request != null && request.getContent() != null) entity.setContent(request.getContent());
//        if (request != null && request.getLocation() != null) entity.setLocation(request.getLocation());
//        if (request != null && request.getWeather() != null) entity.setWeather(request.getWeather());
//        if (request != null && request.getEmotion() != null) entity.setEmotion(request.getEmotion());

        return entity;
    }
}
