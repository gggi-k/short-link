package kr.project.shortlink.api.reposoitory;

import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import kr.project.shortlink.api.repository.ShortLinkRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class ShortLinkRepositoryTest {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    private ShortLinkEntity shortLinkEntity;

    @BeforeEach
    void init() throws MalformedURLException {
        this.shortLinkEntity = ShortLinkEntity.builder()
                .uri(URI.create("https://www.naver.com").toURL())
                .build();
    }

    @Order(1)
    @DisplayName("단축링크 조회")
    @Test
    void findById() throws MalformedURLException {
        shortLinkRepository.save(this.shortLinkEntity);

        Optional<ShortLinkEntity> optionalShortLinkEntity = shortLinkRepository.findById(this.shortLinkEntity.getShortLinkId());

        assertTrue(optionalShortLinkEntity.isPresent());
        ShortLinkEntity shortLinkEntity = optionalShortLinkEntity.get();
        assertEquals(1L, shortLinkEntity.getShortLinkId());
        assertEquals(URI.create("https://www.naver.com").toURL(), shortLinkEntity.getUri());
        assertNotNull(shortLinkEntity.getCreatedAt());
    }

    @Order(2)
    @DisplayName("단축링크 등록")
    @Test
    void create() throws MalformedURLException {
        ShortLinkEntity shortLinkEntity = shortLinkRepository.save(this.shortLinkEntity);

        assertEquals(2L, shortLinkEntity.getShortLinkId());
        assertEquals(URI.create("https://www.naver.com").toURL(), shortLinkEntity.getUri());
        assertNotNull(shortLinkEntity.getCreatedAt());
    }

}