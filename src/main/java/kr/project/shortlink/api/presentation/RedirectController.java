package kr.project.shortlink.api.presentation;

import kr.project.shortlink.ShortLinkApplication;
import kr.project.shortlink.api.application.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URL;

@RequiredArgsConstructor
@Controller
@RequestMapping("/r")
public class RedirectController {

    private final ShortLinkService shortLinkService;

    @GetMapping("/{shortId}")
    public String redirectByShortId(@PathVariable final String shortId) {

        final URL redirectUri = shortLinkService.findUriById(shortId);
        return String.format("redirect:%s", redirectUri.toString());
    }
}