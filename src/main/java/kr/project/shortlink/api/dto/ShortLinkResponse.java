package kr.project.shortlink.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ShortLinkResponse {

    private final String shortId;
    private final String url;
    private final LocalDateTime createdAt;
}