package org.diary.api.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserKakaoLoginRequest {
    @NotBlank
    private Long id;

    @NotBlank
    private Long kakaoUserId;
}
