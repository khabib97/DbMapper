package com.dbmigrator;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisUp {

  static String redisHost;
  static String redisCacheKey;
  static Integer redisPort;

  static Jedis jedis;

  public RedisUp(){
    redisHost = System.getenv("REDIS_CACHE_HOSTNAME");
    redisCacheKey = System.getenv("REDIS_CACHE_KEY");
    redisPort = Integer.parseInt(System.getenv("REDIS_PORT"));

    JedisShardInfo shardInfo = new JedisShardInfo(redisHost, redisPort);
    shardInfo.setPassword(redisCacheKey);
    jedis = new Jedis(shardInfo);
    System.out.println("Connection to server successfully");
    System.out.println("Server is running: "+jedis.ping());
  }

  public static void main(String... args){
    RedisUp redisUp = new RedisUp();
  }
}
