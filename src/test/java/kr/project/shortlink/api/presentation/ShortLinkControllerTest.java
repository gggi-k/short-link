package kr.project.shortlink.api.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ShortLinkController.class)
public class ShortLinkControllerTest {

    @Autowired
    private MockMvc mockMvc;
}