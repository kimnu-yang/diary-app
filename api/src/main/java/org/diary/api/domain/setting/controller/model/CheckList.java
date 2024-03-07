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
public class CheckList {
    private Long id;

    @JsonProperty("registeredAt")
    private LocalDateTime registeredAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;
}
