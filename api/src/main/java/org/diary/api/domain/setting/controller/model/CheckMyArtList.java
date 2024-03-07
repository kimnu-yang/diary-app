package org.diary.api.domain.setting.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckMyArtList {

    private Long id;

    @JsonProperty("artId")
    private Long artId;

    @JsonProperty("baseDate")
    private LocalDateTime baseDate;

    @JsonProperty("registeredAt")
    private LocalDateTime registeredAt;
}
