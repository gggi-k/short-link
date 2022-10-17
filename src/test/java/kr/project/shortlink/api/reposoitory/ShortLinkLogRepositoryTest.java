package kr.project.shortlink.api.reposoitory;

import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import kr.project.shortlink.api.domain.entity.ShortLinkLogEntity;
import kr.project.shortlink.api.domain.vo.ShortLinkLogId;
import kr.project.shortlink.api.repository.ShortLinkLogRepository;
import kr.project.shortlink.api.repository.ShortLinkRepository;
import kr.project.shortlink.core.util.Base62Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ShortLinkLogRepositoryTest {

    @Autowired
    private ShortLinkLogRepository shortLinkLogRepository;

    private ShortLinkLogEntity shortLinkLogEntity;

    @BeforeEach
    void init() throws MalformedURLException {
        this.shortLinkLogEntity = ShortLinkLogEntity.builder()
                .shortLinkLogId(ShortLinkLogId.of(2L, LocalDate.now()))
                .count(4)
                .build();
    }

    @DisplayName("단축링크-로그 등록")
    @Test
    void create() throws MalformedURLException {
        ShortLinkLogEntity shortLinkLogEntity = shortLinkLogRepository.save(this.shortLinkLogEntity);

        assertEquals(2L, shortLinkLogEntity.getShortLinkLogId().getShortLinkId());
        assertNotNull(shortLinkLogEntity.getShortLinkLogId().getLogAt());
        assertEquals(4, shortLinkLogEntity.getCount());
    }

    @DisplayName("단축링크-로그 조회")
    @Test
    void findAllByShortId() throws MalformedURLException {
        shortLinkLogRepository.save(this.shortLinkLogEntity);

        List<ShortLinkLogEntity> shortLinkLogEntities = shortLinkLogRepository.findAllByShortLinkLogId_ShortLinkIdOrderByShortLinkLogId_logAt(this.shortLinkLogEntity.getShortLinkLogId().getShortLinkId());

        assertEquals(1, shortLinkLogEntities.size());
        ShortLinkLogEntity shortLinkLogEntity = shortLinkLogEntities.get(0);
        assertEquals(2L, shortLinkLogEntity.getShortLinkLogId().getShortLinkId());
        assertNotNull(shortLinkLogEntity.getShortLinkLogId().getLogAt());
        assertEquals(4, shortLinkLogEntity.getCount());
    }
}