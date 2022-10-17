package kr.project.shortlink.api.application;

import kr.project.shortlink.api.repository.ShortLinkLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShortLinkLogServiceTest {

    @InjectMocks
    private ShortLinkLogService shortLinkLogService;

    @Mock
    private ShortLinkLogRepository shortLinkLogRepository;

    @DisplayName("단축링크 - 로그 목록 조회")
    @Test
    void findAllByShortId() {

    }


    @DisplayName("단축링크 생성")
    @Test
    void create() {

    }
}