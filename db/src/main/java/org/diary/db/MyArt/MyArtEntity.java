package org.diary.db.MyArt;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.diary.db.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "my_art")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MyArtEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    private Long artId;

    @JsonProperty("baseDate")
    private LocalDateTime baseDate;

    @JsonProperty("registeredAt")
    private LocalDateTime registeredAt;
}
