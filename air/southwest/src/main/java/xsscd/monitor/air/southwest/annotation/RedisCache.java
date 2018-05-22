package xsscd.monitor.air.southwest.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import xsscd.monitor.air.southwest.common.RedisCacheNamespace;

/**
 * 元注解QueryCache 用来标识查询数据库的方法
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
  RedisCacheNamespace nameSpace();
}