package org.diary.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.diary.db")
@EnableJpaRepositories(basePackages = "org.diary.db")
public class JpaConfig {
}
