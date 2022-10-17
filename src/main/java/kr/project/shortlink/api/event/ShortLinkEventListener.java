package kr.project.shortlink.api.event;

import kr.project.shortlink.api.application.ShortLinkLogService;
import kr.project.shortlink.api.dto.ShortLinkLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Component
public class ShortLinkEventListener {

    private static final Map<String, Long> COUNT_MAP = new ConcurrentHashMap<>();

    private final ShortLinkLogService shortLinkLogService;

    @EventListener(ShortLinkEvent.class)
    public void handle(ShortLinkEvent shortLinkEvent) {
        COUNT_MAP.put(shortLinkEvent.getShortId(), COUNT_MAP.getOrDefault(shortLinkEvent.getShortId(), 0L) + 1);
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void schedule() {

        final Map<String, Long> map = new HashMap<>(COUNT_MAP);
        COUNT_MAP.clear();

        for (String shortId : map.keySet()) {
            shortLinkLogService.create(ShortLinkLogRequest.create()
                    .setShortId(shortId)
                    .setLogAt(LocalDate.now())
                    .setCount(map.get(shortId))
            );
        }
    }
}