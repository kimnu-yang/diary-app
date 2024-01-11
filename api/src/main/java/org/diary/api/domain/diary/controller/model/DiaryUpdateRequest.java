package org.diary.api.domain.diary.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.diary.db.diary.enums.Emotion;

@Data
@NotBlank
@AllArgsConstructor
public class DiaryUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String location;

    private String weather;

    @NotBlank
    private Emotion emotion;
}

