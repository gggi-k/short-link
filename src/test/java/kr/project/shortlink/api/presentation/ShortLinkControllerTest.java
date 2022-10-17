package kr.project.shortlink.api.presentation;

import kr.project.shortlink.api.application.ShortLinkService;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@WebMvcTest(ShortLinkController.class)
public class ShortLinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShortLinkService shortLinkService;

    @DisplayName("단축링크 생성")
    @Test
    void create() throws Exception {

        given(shortLinkService.create(any()))
                .willReturn(ShortLinkResponse.builder()
                        .shortId("asdfg")
                        .createdAt(LocalDateTime.now())
                        .uri(URI.create("www.naver.com").toURL())
                        .build());

         mockMvc.perform(post("/short-links"))
                 .andExpect(status().isCreated())
                 .andExpectAll(
                     jsonPath("$.shortId").value("asdfg"),
                     jsonPath("$.createdAt").exists(),
                     jsonPath("$.uri").value("www.naver.com")
                 )
                 .andDo(print());

    }

    @DisplayName("단축링크 조회")
    @Test
    void findById() throws Exception {

        given(shortLinkService.findById(anyString()))
                .willReturn(ShortLinkResponse.builder()
                        .shortId("asdfg")
                        .createdAt(LocalDateTime.now())
                        .uri(URI.create("www.naver.com").toURL())
                        .build());

        mockMvc.perform(get("/short-links/{shortId}", "asdfg"))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.shortId").value("asdfg"),
                        jsonPath("$.createdAt").exists(),
                        jsonPath("$.uri").value("www.naver.com")
                )
                .andDo(print());
    }
}