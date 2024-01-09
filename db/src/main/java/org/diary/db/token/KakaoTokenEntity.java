package org.diary.db.token;

import jakarta.persistence.*;
import lombok.*;
import org.diary.db.BaseEntity;
import org.diary.db.user.UserEntity;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;

@Entity
@Table(name = "kakao_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoTokenEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne
    @MapsId //@MapsId 는 @id로 지정한 컬럼에 @OneToOne 이나 @ManyToOne 관계를 매핑시키는 역할
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(length = 255, nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private LocalDateTime accessTokenExpireAt;

    @Column(nullable = false)
    private String idToken;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private String scopes;
}
