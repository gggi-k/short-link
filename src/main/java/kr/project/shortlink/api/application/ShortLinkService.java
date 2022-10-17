package kr.project.shortlink.api.application;

import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import kr.project.shortlink.api.dto.ShortLinkRequest;
import kr.project.shortlink.api.dto.ShortLinkResponse;
import kr.project.shortlink.api.repository.ShortLinkRepository;
import kr.project.shortlink.core.exception.NotFoundException;
import kr.project.shortlink.core.util.Base62Util;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ShortLinkService {

    private final ShortLinkRepository shortLinkRepository;

    @Cacheable(cacheNames = "shortLinkCreateCache", key = "#shortLinkRequest.uri")
    public ShortLinkResponse create(ShortLinkRequest shortLinkRequest) {

        return ShortLinkResponse.fromEntity(
                shortLinkRepository.findByUri(shortLinkRequest.getUriToUrl())
                .orElseGet(() -> shortLinkRepository.save(
                        ShortLinkEntity.builder()
                                .uri(shortLinkRequest.getUriToUrl())
                                .build()
                )));
    }

    @Cacheable(cacheNames = "shortLinkCache", key = "#shortId")
    @Transactional(readOnly = true)
    public ShortLinkResponse findById(String shortId) {
        return ShortLinkResponse.fromEntity(shortLinkRepository.findById(Base62Util.decode(shortId)).orElseThrow(() -> new NotFoundException("존재하지 않는 아이디 입니다")));
    }
}