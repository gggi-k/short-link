package kr.project.shortlink.api.application;

import kr.project.shortlink.api.dto.ShortLinkRequest;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import kr.project.shortlink.api.repository.ShortLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}