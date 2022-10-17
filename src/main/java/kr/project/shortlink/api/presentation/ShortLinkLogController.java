package kr.project.shortlink.api.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.project.shortlink.api.application.ShortLinkLogService;
import kr.project.shortlink.api.dto.ShortLinkLogResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "단축링크-로그")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/short-links-log")
public class ShortLinkLogController {

    private final ShortLinkLogService shortLinkLogService;

    @Operation(summary = "단축링크-로그 조회")
    @GetMapping("/{shortId}")
    public List<ShortLinkLogResponse> findById(@PathVariable String shortId) {
        return shortLinkLogService.findAllByShortId(shortId);
    }
}