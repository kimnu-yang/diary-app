package org.diary.db.diary;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.diary.db.BaseEntity;
import org.diary.db.diary.enums.Weather;

import java.time.LocalDateTime;

@Entity
@Table(name = "diary")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DiaryEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private Weather weather;

    @JsonProperty("tempNow")
    private String tempNow;

    @JsonProperty("tempMin")
    private String tempMin;

    @JsonProperty("tempMax")
    private String tempMax;

    @JsonProperty("registeredAt")
    private LocalDateTime registeredAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;
}
