package org.diary.api.domain.setting.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.diary.db.diary.DiaryColorEntity;
import org.diary.db.diary.DiaryEntity;
import org.diary.db.diary.DiaryTagEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryWithColorAndTag {
    private DiaryEntity diary;

    @JsonProperty("colors")
    private List<DiaryColorEntity> color;

    @JsonProperty("tags")
    private List<DiaryTagEntity> tag;
}
