package com.spring.springboot.controller;





import com.alibaba.fastjson.JSONObject;
import com.spring.springboot.utils.HttpUtils;

import com.spring.springboot.utils.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class WeixinjsConfigController {

    public static final Logger logger = LoggerFactory.getLogger(WeixinjsConfigController.class);


    @Value("${wechat_appid:none}")
    private String wechat_appid;

    @Value("${wechat_secret:none}")
    private String wechat_secret;

    @Value("${internal.wechat.jssdk.access.token.url:none}")
    private String wechatAccessTokenUrl;

    @Value("${internal.wechat.jssdk.ticket.url:none}")
    private String wechatTicketUrl;

    @Value("${internal.wechat.access.key:none}")
    private String accessKey;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/weixinjs-config-params", method = RequestMethod.GET)
    public Result getInitWechatUrl(@RequestParam String url) {
        Object ticket_obj = redisTemplate.opsForValue().get("ticket");
        Object access_token_obj = redisTemplate.opsForValue().get("access_token");

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
        headerMap.put("Content-Type", "application/json; charset=UTF-8");

        if (access_token_obj == null || access_token_obj.equals("null")){
            Map map = new HashMap();
            map.put("wechatAppId",wechat_appid);
            map.put("wechatSecret", wechat_secret);
            String data = null;
            try {
                if (wechatAccessTokenUrl.equals("none") || wechatAccessTokenUrl.isEmpty()) {
                    return Result.genFail("配置信息wechatAccessTokenUrl不能为空");
                }
                data = HttpUtils.sendPostMethod(wechatAccessTokenUrl,map,headerMap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jsonMap  = JSONObject.parseObject(data);
            if (!jsonMap.get("success").equals(true)) {
                return Result.genFail(jsonMap.get("msg") + "");
            }
            String access_token = String.valueOf(jsonMap.get("data"));
            redisTemplate.opsForValue().set("access_token",access_token,6000, TimeUnit.SECONDS);
            access_token_obj =redisTemplate.opsForValue().get("access_token");
        }

        if (ticket_obj == null || ticket_obj.equals("null")){
            Map map = new HashMap();
            map.put("accessToken",access_token_obj);
            String ticketData = null;
            try {
                if (wechatTicketUrl.equals("none") || wechatTicketUrl.isEmpty()) {
                    return Result.genFail("配置信息wechatTicketUrl不能为空");
                }
                ticketData = HttpUtils.sendPostMethod(wechatTicketUrl,map,headerMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject ticketMap  = JSONObject.parseObject(ticketData);
            if (!ticketMap.get("success").equals(true)) {
                return Result.genFail(ticketMap.get("msg") + "");
            }
            String ticket = String.valueOf(ticketMap.get("data"));
            redisTemplate.opsForValue().set("ticket",ticket,6000, TimeUnit.SECONDS);
            ticket_obj = redisTemplate.opsForValue().get("ticket");
        }

        //获取签名signature
        String noncestr = getRandomStringByLength(16);
        long timestamp = System.currentTimeMillis() / 1000;
        String str = "jsapi_ticket=" + ticket_obj.toString() + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = SHA1(str);
        Map map = new HashMap();
        map.put("noncestr",noncestr);
        map.put("timestamp",timestamp);
        map.put("signature",signature);
        map.put("url",url);
        map.put("appid", wechat_appid);
        map.put("ticket", ticket_obj.toString());
        map.put("access_token", access_token_obj.toString());

        return Result.genSuccess("获取成功").setData(map);
    }


    /**
     * @author：罗国辉
     * @date： 2015年12月17日 上午9:24:43
     * @description： SHA、SHA1加密
     * @parameter：   str：待加密字符串
     * @return：  加密串
     **/
    public static String SHA1(String str) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
//            digest.update(str.getBytes());
            digest.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("SHA1算法加密异常");
        }
    }


    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
