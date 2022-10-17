package kr.project.shortlink.api.application;

import kr.project.shortlink.api.domain.entity.ShortLinkLogEntity;
import kr.project.shortlink.api.domain.vo.ShortLinkLogId;
import kr.project.shortlink.api.dto.ShortLinkLogRequest;
import kr.project.shortlink.api.dto.ShortLinkLogResponse;
import kr.project.shortlink.api.repository.ShortLinkLogRepository;
import kr.project.shortlink.core.util.Base62Util;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ShortLinkLogService {

    private final ShortLinkLogRepository shortLinkLogRepository;

    @Cacheable(cacheNames = "shortLinkLogCache", key = "#shortId")
    @Transactional(readOnly = true)
    public List<ShortLinkLogResponse> findAllByShortId(final String shortId) {
        return ShortLinkLogResponse.fromEntities(shortLinkLogRepository.findAllByShortLinkLogId_ShortLinkIdOrderByShortLinkLogId_logAt(Base62Util.decode(shortId)));
    }

    @CacheEvict(cacheNames = "shortLinkLogCache", key = "#shortLinkLogRequest.shortId")
    public ShortLinkLogResponse create(ShortLinkLogRequest shortLinkLogRequest) {

        final ShortLinkLogId shortLinkLogId = ShortLinkLogId.of(Base62Util.decode(shortLinkLogRequest.getShortId()), shortLinkLogRequest.getLogAt());
        final Optional<ShortLinkLogEntity> optionalLogEntity = shortLinkLogRepository.findById(shortLinkLogId);

        final ShortLinkLogEntity shortLinkLogEntity = optionalLogEntity.orElse(ShortLinkLogEntity.builder()
                .shortLinkLogId(shortLinkLogId)
                .count(shortLinkLogRequest.getCount())
                .build());

        if(optionalLogEntity.isPresent()) {
            shortLinkLogEntity.addCount(shortLinkLogRequest.getCount());
        }

        return ShortLinkLogResponse.fromEntity(shortLinkLogRepository.save(shortLinkLogEntity));
    }
}