package kr.project.shortlink.api.domain.entity;

import kr.project.shortlink.api.domain.vo.ShortLinkLogId;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "SHORT_LINK_LOG")
@Entity
public class ShortLinkLogEntity {

    @EmbeddedId
    private ShortLinkLogId shortLinkLogId;

    @Column(name = "COUNT", nullable = false)
    @Comment("횟수")
    @ColumnDefault("'0'")
    private long count;
}