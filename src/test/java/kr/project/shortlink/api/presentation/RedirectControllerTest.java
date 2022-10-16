package kr.project.shortlink.api.presentation;

import kr.project.shortlink.api.application.ShortLinkService;
import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;


@WebMvcTest(RedirectController.class)
public class RedirectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortLinkService shortLinkService;

    @DisplayName("리다이렉션 - 단축링크")
    @Test
    void redirectByShortId() throws Exception {
        final String redirectUri = "https://www.naver.com";

        given(shortLinkService.findById(anyString()))
                .willReturn(ShortLinkResponse.fromEntity(
                        ShortLinkEntity.builder()
                                .uri(URI.create(redirectUri).toURL())
                                .build()
                ));

        mockMvc.perform(get("/r/{shortId}", "123"))
                .andExpect(status().isFound())
                .andExpectAll(redirectedUrl(redirectUri))
                .andDo(print());
    }
}