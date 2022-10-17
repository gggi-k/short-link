package kr.project.shortlink.api.repository;

import kr.project.shortlink.api.domain.entity.ShortLinkLogEntity;
import kr.project.shortlink.api.domain.vo.ShortLinkLogId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortLinkLogRepository extends JpaRepository<ShortLinkLogEntity, ShortLinkLogId> {

    List<ShortLinkLogEntity> findAllByShortLinkLogId_ShortLinkIdOrderByShortLinkLogId_logAt(Long shortId);
}