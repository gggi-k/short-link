package kr.project.shortlink.api.dto;

import kr.project.shortlink.api.domain.entity.ShortLinkLogEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(staticName = "create")
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class ShortLinkLogRequest {

    private String shortId;
    private LocalDate logAt;
    private long count;
}