package kr.project.shortlink.api.domain.entity;

import kr.project.shortlink.api.domain.vo.ShortLinkLogId;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "SHORT_LINK")
@Entity
public class ShortLinkLogEntity {

    @EmbeddedId
    private ShortLinkLogId shortLinkLogId;

    @Column(name = "COUNT", nullable = false)
    @Comment("횟수")
    @ColumnDefault("'0'")
    private long count;
}