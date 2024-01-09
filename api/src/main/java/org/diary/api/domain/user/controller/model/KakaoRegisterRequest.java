package org.diary.api.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoRegisterRequest {
    @NotBlank
    private String accessToken;

    @NotBlank
    private LocalDateTime accessTokenExpireAt;

    @NotBlank
    private String idToken;

    @NotBlank
    private String refreshToken;

    @NotBlank
    private String scopes;
}
