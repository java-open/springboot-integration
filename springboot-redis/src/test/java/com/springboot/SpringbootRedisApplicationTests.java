package com.springboot;

import com.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class SpringbootRedisApplicationTests {
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testSet() {
		User user = new User();
		user.setId(1);
		user.setUsername("张三");
		user.setAge(18);
		redisTemplate.opsForValue().set("user:" + user.getId(), user);

		User u = (User) redisTemplate.opsForValue().get("user:1");
		System.out.println(u);
	}

	@Test
	public void testGet() {
		User user = (User) redisTemplate.opsForValue().get("user:1");
		System.out.println(user);
	}

}
