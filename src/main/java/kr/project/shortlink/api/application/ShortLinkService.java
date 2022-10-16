package kr.project.shortlink.api.application;

import kr.project.shortlink.api.dto.ShortLinkRequest;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import kr.project.shortlink.api.dto.ShortLinkUriProjection;
import kr.project.shortlink.api.repository.ShortLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URL;

@Transactional
@RequiredArgsConstructor
@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;

    public ShortLinkResponse create(ShortLinkRequest shortLinkRequest) {
        return null;
    }

    @Transactional(readOnly = true)
    public ShortLinkResponse findById(@PathVariable String shortId) {
        return null;
    }

    @Transactional(readOnly = true)
    public URL findUriById(@PathVariable String shortId) {
        return shortLinkRepository.findByShortId(shortId, ShortLinkUriProjection.class).getUri();
    }
}