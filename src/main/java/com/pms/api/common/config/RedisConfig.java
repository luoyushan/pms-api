package com.pms.api.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

  /*
   * 定义缓存数据 key 生成策略的bean 包名+类名+方法名+所有参数
   */
  @Bean("keyGenerator")
  public KeyGenerator keyGenerator(){
    return (target, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append(target.getClass().getName());
      sb.append("::");
      sb.append(method.getName());
      sb.append("::");
      ObjectMapper mapper = new ObjectMapper();
      for (Object obj : params) {
        try {
          sb.append(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
      }
      return sb.toString();
    };

  }
  private Duration timeToLive = Duration.ZERO;
  public void setTimeToLive(Duration timeToLive) {
    this.timeToLive = timeToLive;
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory factory) {
    RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

    //解决查询缓存转换异常的问题
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);

    // 配置序列化（解决乱码的问题）
    RedisCacheConfiguration config = RedisCacheConfiguration
        .defaultCacheConfig()
        .entryTtl(timeToLive)
        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
        .disableCachingNullValues();

    return RedisCacheManager.builder(factory)
        .cacheDefaults(config)
        .build();
  }
}
