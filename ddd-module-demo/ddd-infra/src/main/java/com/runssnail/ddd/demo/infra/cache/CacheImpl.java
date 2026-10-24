package com.runssnail.ddd.demo.infra.cache;

import com.runssnail.ddd.demo.domain.cache.Cache;
import org.springframework.stereotype.Component;

@Component
public class CacheImpl implements Cache {

    @Override
    public String getString(String key) {
        return "";
    }

    @Override
    public int getInt(String key) {
        return 0;
    }
}
