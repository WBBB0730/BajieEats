package cn.edu.szu.Bajie.config;

import cn.edu.szu.Bajie.util.CacheService;
import cn.edu.szu.Bajie.util.FastJsonRedisSerializer;
import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/7 22:03
 * @version 1.0
 */
@Slf4j
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${caching.cachingPrefix:BAJIE::}")
    private String cachePrefix;

    @Value("${caching.defaultTtl:60}")
    private long cachingDefaultTime;

//    @Value("#{${caching.ttlParams}}")
//    private Map<String,Long> ttlParams;

    public RedisConfig() {
        super();
    }


    @Override
    public CacheResolver cacheResolver() {
        return super.cacheResolver();
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {

        /**
         * target: 类
         * method: 方法
         * params: 方法参数
         */
        return (target, method, params) -> {
            //获取代理对象的最终目标对象
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getSimpleName()).append("::");
            sb.append(method.getName()).append("::");
            //调用SimpleKey的key生成器
            Object key = SimpleKeyGenerator.generateKey(params);
            return sb.append(key);
        };

        //return super.keyGenerator();
    }

    //缓存的异常处理
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                log.error("Redis occur handleCacheClearError：", e);
            }
        };
    }



    @Bean
    public CacheService cacheService(RedisConnectionFactory connectionFactory){

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

        stringRedisTemplate.setConnectionFactory(connectionFactory);

        stringRedisTemplate.afterPropertiesSet();

        return new CacheService(stringRedisTemplate);
    }

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * springCache配置
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {

        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);

        return RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .prefixCacheNameWith(cachePrefix)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .entryTtl(Duration.ofMinutes(cachingDefaultTime));
    }
}
