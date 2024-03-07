package org.diary.api.domain.setting.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.diary.db.diary.DiaryColorEntity;
import org.diary.db.diary.DiaryTagEntity;
import org.diary.db.diary.enums.Weather;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryUploadData {

    static class Diary {
        @JsonProperty("id")
        private Long id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("content")
        private String content;

        @JsonProperty("weather")
        private Weather weather;

        @JsonProperty("tempNow")
        private String tempNow;

        @JsonProperty("tempMin")
        private String tempMin;

        @JsonProperty("tempMax")
        private String tempMax;

        @JsonProperty("registeredAt")
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private String registeredAt;

        @JsonProperty("updatedAt")
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private String updatedAt;

        @JsonProperty("deletedAt")
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private String deletedAt;
    }

    private Diary diary;

    @JsonProperty("colors")
    private List<DiaryColorEntity> color;

    @JsonProperty("tags")
    private List<DiaryTagEntity> tag;

//    {"content":"1343143","id":2,"isTemp":"N","registeredAt":{},"title":"35322352352"}
}
