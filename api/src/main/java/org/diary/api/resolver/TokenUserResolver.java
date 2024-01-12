package org.diary.api.resolver;

import lombok.RequiredArgsConstructor;
import org.diary.api.common.annotation.TokenUser;
import org.diary.api.domain.user.service.UserService;
import org.diary.db.user.UserEntity;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class TokenUserResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. 어노테이션이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(TokenUser.class);

        // 2. 파라미터 타입 체크
        var parameterType = parameter.getParameterType().equals(UserEntity.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // support parameter 에서 true 반환시 여기 실행

        // request context holder에서 값 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();
        var userId = requestContext != null ? requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST) : null;

        var userEntity = userService.getUserWithThrow(Long.parseLong(userId != null ? userId.toString() : "0"));

        // 사용자 정보 셋팅
        return UserEntity.builder()
                .id(userEntity.getId())
                .kakaoUserId(userEntity.getKakaoUserId())
                .googleUserId(userEntity.getGoogleUserId())
                .status(userEntity.getStatus())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
