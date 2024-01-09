package org.diary.api.domain.diary.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.diary.db.diary.enums.Emotion;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRegisterRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String location;

    private String weather;

    @NotBlank
    private Emotion emotion;
}
