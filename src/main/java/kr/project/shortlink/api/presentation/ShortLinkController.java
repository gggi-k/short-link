package kr.project.shortlink.api.presentation;

import kr.project.shortlink.api.application.ShortLinkService;
import kr.project.shortlink.api.dto.ShortLinkRequest;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/short-links")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    @PostMapping
    public ShortLinkResponse create(@RequestBody ShortLinkRequest shortLinkRequest) {
        return shortLinkService.create(shortLinkRequest);
    }

    @GetMapping("/{shortId}")
    public ShortLinkResponse findById(@PathVariable String shortId) {
        return shortLinkService.findById(shortId);
    }
}