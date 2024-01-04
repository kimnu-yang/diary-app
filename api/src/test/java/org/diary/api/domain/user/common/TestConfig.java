package org.diary.api.domain.user.common;

import org.diary.api.ApiApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ContextConfiguration(classes = ApiApplication.class)
@Transactional
public class TestConfig {
}
