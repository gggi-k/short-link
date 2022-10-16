package kr.project.shortlink.api.domain.vo;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@Embeddable
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShortLinkLogId implements Serializable {

    private static final long serialVersionUID = -2912049713061475943L;

    @Column(name = "SHORT_ID")
    @Comment("아이디")
    private String shortId;

    @Column(name = "LOG_AT")
    @Comment("로그일시")
    private LocalDate logAt;
}