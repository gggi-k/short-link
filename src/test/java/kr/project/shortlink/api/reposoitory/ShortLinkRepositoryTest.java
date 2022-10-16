package kr.project.shortlink.api.reposoitory;

import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import kr.project.shortlink.api.repository.ShortLinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ShortLinkRepositoryTest {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    private ShortLinkEntity shortLinkEntity;

    @BeforeEach
    void init() throws MalformedURLException {
        this.shortLinkEntity = ShortLinkEntity.builder()
                .shortLinkId(1L)
                .uri(URI.create("https://www.naver.com").toURL())
                .build();
    }

    @DisplayName("단축링크 등록")
    @Test
    void create() throws MalformedURLException {
        ShortLinkEntity shortLinkEntity = shortLinkRepository.save(this.shortLinkEntity);

        assertEquals(1L, shortLinkEntity.getShortLinkId());
        assertEquals(URI.create("https://www.naver.com").toURL(), shortLinkEntity.getUri());
        assertNotNull(shortLinkEntity.getCreatedAt());
    }

    @DisplayName("단축링크 조회")
    @Test
    void findById() throws MalformedURLException {
        shortLinkRepository.save(this.shortLinkEntity);

        ShortLinkEntity shortLinkEntity = shortLinkRepository.findById(this.shortLinkEntity.getShortLinkId()).get();

        assertEquals(1L, shortLinkEntity.getShortLinkId());
        assertEquals(URI.create("https://www.naver.com").toURL(), shortLinkEntity.getUri());
        assertNotNull(shortLinkEntity.getCreatedAt());
    }
}