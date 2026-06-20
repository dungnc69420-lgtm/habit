package com.habit.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        var manager = new CaffeineCacheManager(
                "habitTemplates",
                "goalUnits",
                "habitCategories"
        );
        
        manager.setCacheSpecification("maximumSize=1000,expireAfterWrite=1h");
        return manager;
    }
}
