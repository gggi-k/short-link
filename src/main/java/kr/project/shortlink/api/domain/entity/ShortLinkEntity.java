package kr.project.shortlink.api.domain.entity;

import kr.project.shortlink.core.util.Base62Util;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "SHORT_LINK",
    uniqueConstraints = @UniqueConstraint(columnNames = "URI")
)
@Entity
public class ShortLinkEntity {

    @Id
    @GeneratedValue
    @Column(name = "SHORT_LINK_ID")
    @Comment("아이디")
    private Long shortLinkId;

    @NaturalId
    @Column(name = "URI", updatable = false, nullable = false)
    @Comment("URI")
    private URL uri;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    @Builder.Default
    @ColumnDefault("'CURRENT_TIMESTAMP'")
    @Comment("생성일시")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Transient
    public String getShortId() {
        return Base62Util.encode(this.shortLinkId);
    }
}