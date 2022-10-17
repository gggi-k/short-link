package kr.project.shortlink.core.aop;

import kr.project.shortlink.api.event.ShortLinkEvent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Aspect
public class ShortLinkLogAspect {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(value = "@annotation(kr.project.shortlink.core.aop.annotation.ShortLinkLogCount)", argNames = "shortId")
    public void before(String shortId) {
        eventPublisher.publishEvent(ShortLinkEvent.of(shortId));
    }
}