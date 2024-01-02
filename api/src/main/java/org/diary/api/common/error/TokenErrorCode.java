package org.diary.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * UserError는 1000번대 코드 사용
 */
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{

    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400,2001, "만료된 토큰"),
    TOKEN_EXCEPTION(400, 2002, "알수없는 토큰 에러"),
    TOKEN_NOT_FOUND(400,2003, "인증 헤더 토큰 없음");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
