package kr.project.shortlink.api.repository;

import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortLinkRepository extends JpaRepository<ShortLinkEntity, String> {
}