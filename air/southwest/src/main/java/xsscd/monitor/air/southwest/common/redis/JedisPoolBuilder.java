package xsscd.monitor.air.southwest.common.redis;


import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolBuilder {
    @Autowired
    private static JedisPool jedisPool;

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void release(Jedis jedis) {
        jedisPool.returnBrokenResource(jedis);

}
}
