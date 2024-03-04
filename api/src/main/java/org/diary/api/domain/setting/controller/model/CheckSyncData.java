package org.diary.api.domain.setting.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckSyncData {

    @JsonProperty("lastSyncTime")
    private String lastSyncTime;

    @JsonProperty("checkList")
    private List<CheckList> checkLists;
}
