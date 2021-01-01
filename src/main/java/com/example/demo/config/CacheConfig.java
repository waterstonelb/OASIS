package com.example.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
@Profile("!test")
public class CacheConfig {

    @Value("${redis.hostname:localhost}")
    private String redisHostName;

    @Value("${redis.port:6379}")
    private int redisPort;

    @Value("${redis.ttl.hours:1}")
    private int redisDataTTL;

    @Value("${redis.password:shkb}")
    private String redisPwd;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration=new RedisStandaloneConfiguration(redisHostName, redisPort);
        standaloneConfiguration.setPassword(redisPwd);
        return new LettuceConnectionFactory(standaloneConfiguration);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().disableCachingNullValues()
                .entryTtl(Duration.ofHours(redisDataTTL))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java()));

        redisCacheConfiguration.usePrefix();


        RedisCacheManager redisCacheManager = RedisCacheManager
                .RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(getConfig()).build();

        return redisCacheManager;

    }

    private Map<String, RedisCacheConfiguration> getConfig(){
        Map<String, RedisCacheConfiguration> configs = new HashMap<>();
        configs.put("affiliationFigureCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        configs.put("authorFigureCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        configs.put("fieldFigureCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        configs.put("affRankingCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        configs.put("affDetailCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        configs.put("authorRankingCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        configs.put("authorDetailCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        configs.put("fieldWcCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(240)));

        return configs;

    }


}

