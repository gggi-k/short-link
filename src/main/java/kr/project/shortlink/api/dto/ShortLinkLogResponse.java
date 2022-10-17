package kr.project.shortlink.api.dto;

import kr.project.shortlink.api.domain.entity.ShortLinkLogEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.mapstruct.Mapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
public class ShortLinkLogResponse implements Serializable {

    private static final long serialVersionUID = 789900944769522915L;

    @Mapper
    interface Mappers {
        ShortLinkLogResponse$MappersImpl IMPL = new ShortLinkLogResponse$MappersImpl();
        ShortLinkLogResponse fromEntity(ShortLinkLogEntity shortLinkLogEntity);
    }

    private final String shortId;

    private final LocalDate logAt;

    private final long count;

    public static ShortLinkLogResponse fromEntity(ShortLinkLogEntity shortLinkLogEntity) {
        return Mappers.IMPL.fromEntity(shortLinkLogEntity);
    }

    public static List<ShortLinkLogResponse> fromEntities(List<ShortLinkLogEntity> shortLinkLogEntities) {
        return shortLinkLogEntities.stream().map(ShortLinkLogResponse::fromEntity).collect(Collectors.toList());
    }
}