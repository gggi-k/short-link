package kr.project.shortlink.api.presentation;

import kr.project.shortlink.api.application.ShortLinkLogService;
import kr.project.shortlink.api.dto.ShortLinkLogResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@WebMvcTest(ShortLinkLogController.class)
public class ShortLinkLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortLinkLogService shortLinkLogService;

    @DisplayName("단축링크로그조회")
    @Test
    void findById() throws Exception {

        final String shortId = "sbsdf";

        given(shortLinkLogService.findAllByShortId(anyString()))
                .willReturn(List.of(ShortLinkLogResponse.builder()
                        .shortId(shortId)
                        .logAt(LocalDate.now())
                        .count(3)
                        .build()));

        mockMvc.perform(get("/short-links-log/{shortId}", shortId))
                .andExpect(status().isOk())
                .andExpectAll(
                    jsonPath("$[*].shortId").value(shortId),
                    jsonPath("$[*].logAt").exists(),
                    jsonPath("$[*].count").value(3)
                )
                .andDo(print());

    }
}