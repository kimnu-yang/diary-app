package org.diary.api.domain.user.model;


import io.jsonwebtoken.lang.Assert;
import org.diary.api.domain.user.common.TestConfig;
import org.diary.db.user.UserEntity;
import org.diary.db.user.UserRepository;
import org.diary.db.user.enums.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserTest extends TestConfig {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        System.out.println("각 테스트 케이스 실행 전 실행될 소스");
    }

    @Test
    @DisplayName("유저 생성 테스트")
    void createUserTest() {
        // given : 테스트할 값을 설정
        // when : given에서 설정한 값으로 진행할 테스트 설정
        // then : 테스트가 모두 끝났을 때 기대하는 결과와 같은지 확인

        // 1. assertThat은 값 검증에 쓰입니다.
        // assertThat(실제값). isEqualTo(기댓값)
        // assertThat(실제 객체). isInstanceOf(객체 예상 타입)
        // assertThat(실제값). isNull()
        // 등등 실제 값, 값의 타입을 비교하는 여러 연산자들과 쓰이는 메서드입니다.
        // 2. asserThatThrownBy는 예외 발생 검증에 쓰입니다.
        // asserThatThrownBy( () -> 예외를 발생시킬 로직). isInstanceOf(예외 클래스)

        // given
        UserEntity user = UserEntity.builder().id(1L).name("테스트").password("1234").email("email@gamil.com").address("테스트 주소").status(UserStatus.REGISTERED).build();

        // when
        Object obj = userRepository.save(user);

        // then
        Assert.notNull(obj, "저장 실패");
    }

    @Test
    public void test() {
        System.out.println("test 함수");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("각 테스트 케이스 실행 후 실행될 소스");
    }
}