package org.diary.api.domain.question.converter;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.Converter;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.question.controller.model.QuestionResponse;
import org.diary.db.question.QuestionEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class QuestionConverter {

    public QuestionResponse toResponse(QuestionEntity questionEntity) {
        return Optional.ofNullable(questionEntity)
                .map(it -> {
                    return QuestionResponse.builder()
                            .id(questionEntity.getId())
                            .question(questionEntity.getQuestion())
                            .createdAt(questionEntity.getCreatedAt())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
