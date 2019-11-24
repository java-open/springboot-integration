package com.shsxt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 消费服务
 */
@RestController
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/consumer")
    public String consumer() {
        // 获取服务列表
        List<String> serviceList = discoveryClient.getServices();
        // 根据服务名称获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceList.get(0));
        // 处理要远程调用的接口
        ServiceInstance serviceInstance = serviceInstances.get(0);
        // http://localhost:8081/hello
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello";
        // 通过RestTemplate发起请求
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("服务提供者返回的数据是：" + result);
        return result;
    }

}
