package org.diary.api.domain.diary.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkUpdateRequest {

    @NotBlank
    private Long diaryId;

    @NotBlank
    private Long userId;

    @NotBlank
    private int position;
}
