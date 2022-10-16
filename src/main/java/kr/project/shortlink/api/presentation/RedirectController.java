package kr.project.shortlink.api.presentation;

import kr.project.shortlink.api.application.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/r")
public class RedirectController {

    private final ShortLinkService shortLinkService;

    @GetMapping("/{shortId}")
    public RedirectView redirectByShortId(@PathVariable final String shortId) {
        return new RedirectView(shortLinkService.findUriById(shortId).toString());
    }
}