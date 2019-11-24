package com.springboot.config;

//@Configuration
public class RedisConfigForJedis {

    /**
     * Jedis
     */
    //@Bean
    /*
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")// 主节点名称
                .sentinel("192.168.10.10", 26379)
                .sentinel("192.168.10.10", 26380)
                .sentinel("192.168.10.10", 26381);
        sentinelConfig.setPassword("root");
        return new JedisConnectionFactory(sentinelConfig);
    }
     */

    // 重写 RedisTemplate 序列化
    //@Bean
    /*
    public RedisTemplate<String, Object> redisTemplate(
            JedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 为 String 类型 key 设置序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 为 String 类型 value 设置序列化器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 为 Hash 类型 key 设置序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        // 为 Hash 类型 value 设置序列化器
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    */

}
