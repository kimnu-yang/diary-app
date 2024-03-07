package org.diary.api.common.api.kakao;

import lombok.extern.slf4j.Slf4j;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
public class KakaoApi {

    /**
     * 카카오 토큰을 통해 카카오 ID 가져오는 함수
     *
     * @param token - 카카오 유저 토큰
     * @return 카카오 유저 ID
     */
    static public Long getUserId(String token) {
        final var url = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplate();

        // 요청 매개변수 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> requestMessage = new HttpEntity<>(null, headers);

        ResponseEntity<KakaoUserInfo> response = null;
        try {
            response = restTemplate.postForEntity(url, requestMessage, KakaoUserInfo.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                log.error("사용자 카카오토큰 UNAUTHORIZED 오류 [" + e.getLocalizedMessage() + " [" + token + "]");
                throw new ApiException(ErrorCode.SERVER_ERROR, "사용자 카카오토큰 UNAUTHORIZED 오류");
            }
            
            log.error("카카오 통신 오류 [" + e.getLocalizedMessage() + "]");
            throw new ApiException(ErrorCode.SERVER_ERROR, "카카오 통신 오류");
        }

        Optional<KakaoUserInfo> opt = Optional.ofNullable(response.getBody());

        Long userId;
        if (opt.isPresent()) {
            userId = opt.get().getId();
        } else {
            throw new ApiException(ErrorCode.SERVER_ERROR, "카카오 UserId 파싱 오류");
        }

        return userId;
    }
}
