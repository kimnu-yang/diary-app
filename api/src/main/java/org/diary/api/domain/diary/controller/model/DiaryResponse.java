package org.diary.api.domain.diary.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.diary.db.diary.enums.Emotion;
import org.diary.db.user.enums.DiaryStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryResponse {

    private Long id;

    private String title;

    private String content;

    private String location;

    private String weather;

    private Emotion emotion;

    private DiaryStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
