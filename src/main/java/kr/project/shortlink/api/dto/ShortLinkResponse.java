package kr.project.shortlink.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.project.shortlink.api.domain.entity.ShortLinkEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.mapstruct.Mapper;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ShortLinkResponse implements Serializable {

    private static final long serialVersionUID = 3007049024669126380L;

    @Mapper
    interface Mappers {
        ShortLinkResponse$MappersImpl IMPL = new ShortLinkResponse$MappersImpl();
        ShortLinkResponse fromEntity(ShortLinkEntity shortLinkEntity);
    }

    @Schema(description = "아이디", example = "absdf")
    private final String shortId;

    @Schema(description = "링크", example = "https://www.naver.com")
    private final URL uri;

    @Schema(description = "생성일시")
    private final LocalDateTime createdAt;

    public static ShortLinkResponse fromEntity(ShortLinkEntity shortLinkEntity) {
        return Mappers.IMPL.fromEntity(shortLinkEntity);
    }



}