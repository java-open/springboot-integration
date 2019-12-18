package com.gitee.taven.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 *@Description  在业务方法执行前，获取当前用户的token或者JSessionId+当前请求地址，作为一个唯一的key，去获取redis分布式锁，如果此时并发获取，只有一个线程能获取到。
 * 业务执行后，释放锁
 * Redis 分布式锁实现
 * lua 表达式为了保持数据的原子性。
 *
 * @see <a href="https://redis.io/commands/setnx">SETNX key value</a>
 * @see <a href="https://www.cnblogs.com/linjiqin/p/8003838.html">Redis分布式锁的正确实现方式</a>
 */

@Service
public class RedisLock2{

        @Autowired
        private RedisTemplate redisTemplate;

        /**
         * redis 锁成功标识常量
         */
        private static final Long RELEASE_SUCCESS = 1L;
        /**
         * 加锁 Lua 表达式。
         */
        private static final String RELEASE_TRY_LOCK_LUA =
                "if redis.call('setNx',KEYS[1],ARGV[1]) == 1 then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end";
        /**
         * 解锁 Lua 表达式.
         */
        private static final String RELEASE_RELEASE_LOCK_LUA =
                "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        /**
         * 加锁
         * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
         * 对于 Redis 集群则无法使用
         * <p>
         * 支持重复，现成安全
         *
         * @param lockKey    加锁键
         * @param userId     加锁客户端唯一标识（采用用户id, 需要把用户 id 转换为 String 类型）
         * @param expireTime 锁过期时间
         * @return 1 如果key被设置了  0 如果key没有被设置
         */
        public boolean tryLock(String lockKey, String userId, long expireTime) {
            try {
                RedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_TRY_LOCK_LUA, Long.class);
                Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), userId, expireTime);
                if (RELEASE_SUCCESS.equals(result)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        /**
         * 解锁
         * 与 tryLock 相对应，用作释放锁
         * 解锁必须与加锁是同一人，其他人拿到锁也不可以解锁 （解铃还须系铃人）
         *
         * @param lockKey 加锁键
         * @param userId  解锁客户端唯一标识（采用用户id, 需要把用户 id 转换为 String 类型）
         * @return
         */
        public boolean releaseLock(String lockKey, String userId) {
            RedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_RELEASE_LOCK_LUA, Long.class);
            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), userId);
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }

            return false;
        }
    }


