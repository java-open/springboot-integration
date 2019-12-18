package com.gitee.taven.test;

import com.gitee.taven.controller.SubmitController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RunTest implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunTest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("执行多线程测试");
        String url="http://localhost:8000/submit";
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //"userId" + i;
        for(int i=0; i<10; i++){
            String userId = ""+i;
            HttpEntity request = buildRequest(userId);
            System.out.println(request.getBody().toString()+"//////////////////");
            executorService.submit(() -> {
                try {
                    countDownLatch.await();
                    System.out.println("Thread:"+Thread.currentThread().getName()+", time:"+System.currentTimeMillis());
                    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
                    System.out.println("Thread:"+Thread.currentThread().getName() + "-------->" + response.getBody());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.countDown();
    }

    private HttpEntity buildRequest(String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("userId",userId);
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4sQVVUSF9XUklURSIsInN1YiI6ImFkbWluaXN0cmF0b3IiLCJhdWQiOiJQQyIsImp0aSI6IjEiLCJpYXQiOjE1NzY0NzY3NzcsImV4cCI6MTU3NzA4MTU3N30.Kkd4302G-WGDTNGjjGWQ8DFDQPPgNqRL4TDqDJ6Sh4MIugeVKpg3nO6w-B_3H09P9tDW5GN5TGjjmEGSuQjXJw");
        Map<String, Object> body = new HashMap<>();
        body.put("userId", userId);
        return new HttpEntity<>(body, headers);
    }

}
