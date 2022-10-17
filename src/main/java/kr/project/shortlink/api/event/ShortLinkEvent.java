package kr.project.shortlink.api.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
public class ShortLinkEvent {

    private final String shortId;
}