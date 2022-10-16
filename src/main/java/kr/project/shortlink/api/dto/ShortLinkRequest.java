package kr.project.shortlink.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import java.net.MalformedURLException;
import java.net.URI;

@Slf4j
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class ShortLinkRequest {

    @Schema(description = "링크", example = "https://www.naver.com")
    @URL
    @NotBlank
    private String uri;

    @Schema(hidden = true)
    public java.net.URL getUriToUrl() {
        try {
            return URI.create(this.uri).toURL();
        } catch (MalformedURLException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}