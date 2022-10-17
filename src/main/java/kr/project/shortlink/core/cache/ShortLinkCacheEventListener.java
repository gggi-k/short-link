package kr.project.shortlink.core.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import java.text.MessageFormat;

public class ShortLinkCacheEventListener implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        System.out.println(MessageFormat.format("{0}, {1}, {2}, {3}", event.getKey(), event.getNewValue(), event.getOldValue(), event.getType()));
    }
}