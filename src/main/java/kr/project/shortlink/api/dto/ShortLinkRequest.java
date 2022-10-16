package kr.project.shortlink.api.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
@ToString
public class ShortLinkRequest {

    private String url;
}