package config;

import org.aeonbits.owner.ConfigCache;

public class PropertyUtil {

    public static PropertyConfig getConfig() {
        return ConfigCache.getOrCreate(PropertyConfig.class);
    }
}

