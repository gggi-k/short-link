package kr.project.shortlink.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import java.text.MessageFormat;

@Slf4j
public class ShortLinkCacheEventListener implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        log.info("cache - {}, {}, {}, {}", event.getKey(), event.getNewValue(), event.getOldValue(), event.getType());
    }
}