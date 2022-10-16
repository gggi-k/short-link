package kr.project.shortlink.api.application;

import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import kr.project.shortlink.api.dto.ShortLinkRequest;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import kr.project.shortlink.api.dto.ShortLinkUriProjection;
import kr.project.shortlink.api.exception.NotFoundException;
import kr.project.shortlink.api.repository.ShortLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URL;

@Transactional
@RequiredArgsConstructor
@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;

    public ShortLinkResponse create(ShortLinkRequest shortLinkRequest) {
        return ShortLinkResponse.fromEntity(shortLinkRepository.save(
                ShortLinkEntity.builder()
                        .uri(shortLinkRequest.getUriToUrl())
                        .build()
        ));
    }

    @Cacheable(cacheNames = "shortLinkCache", key = "#shortId")
    @Transactional(readOnly = true)
    public ShortLinkResponse findById(@PathVariable String shortId) {
        return ShortLinkResponse.fromEntity(shortLinkRepository.findById(shortId).orElseThrow(() -> new NotFoundException("존재하지 않는 아이디 입니다")));
    }
}