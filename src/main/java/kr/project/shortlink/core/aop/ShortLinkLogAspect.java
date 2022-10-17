package kr.project.shortlink.core.aop;

import kr.project.shortlink.api.event.ShortLinkEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Aspect
public class ShortLinkLogAspect {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(pointcut = "@annotation(kr.project.shortlink.core.aop.annotation.ShortLinkLogCount)")
    public void afterReturning(JoinPoint joinPoint) {
        if(joinPoint.getArgs().length == 0) throw new IllegalArgumentException("required shortId");
        final String shortId = ((String) joinPoint.getArgs()[0]);
        eventPublisher.publishEvent(ShortLinkEvent.of(shortId));

        log.info("shortId = {} count up!!", shortId);
    }
}