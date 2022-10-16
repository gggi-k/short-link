package kr.project.shortlink.api.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "SHORT_LINK")
@Entity
public class ShortLinkEntity {

    @Id
    @Column(name = "SHORT_ID")
    @Comment("아이디")
    private String shortId;

    @Column(name = "URI", updatable = false, nullable = false)
    @Comment("URI")
    private URL uri;

    @CreatedDate
    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    @Builder.Default
    @ColumnDefault("'CURRENT_TIMESTAMP'")
    @Comment("생성일시")
    private LocalDateTime createdAt = LocalDateTime.now();
}