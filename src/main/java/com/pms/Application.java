package com.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;

@CacheConfig(cacheNames = {"myCache"})
@SpringBootApplication
@EnableCaching
@MapperScan("com.pms.api")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
