package org.diary.api.domain.bookmark.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkResponse {

    private Long id;

    private Long diaryId;

    private Long userId;

    private int position;
}
