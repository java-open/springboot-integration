package com.spring.springboot.utils;



import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


@Slf4j
public class HttpUtils {

    private static final int HTTP_STATUS_OK = 200;
    /**
     * 连接超时（单位：毫秒）
     */
    private static int connectTimeOut = 5000;

    /**
     * 读取数据超时（单位：毫秒）
     */
    private static int readTimeOut = 30000;

    /**
     * 请求编码
     */
    private static String requestEncoding = "UTF-8";

    public static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);


    /**
     * <pre>
     * 发送带参数的GET的HTTP请求
     * </pre>
     *
     * @param reqUrl
     *            HTTP请求URL
     * @param parameters
     *            参数映射表
     * @return HTTP响应的字符串
     * @throws UnsupportedEncodingException
     */
    public static String doGet(String reqUrl, Map parameters, String recvEncoding) {
        StringBuffer params = new StringBuffer();
        for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
            Entry element = (Entry) iter.next();
            params.append(element.getKey().toString());
            params.append("=");
            try {
                params.append(URLEncoder.encode(element.getValue().toString(), HttpUtils.requestEncoding));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
            params.append("&");
        }
        if (params.length() > 0) {
            params = params.deleteCharAt(params.length() - 1);
        }
        reqUrl += "?" + params.toString();

        return doGet(reqUrl, recvEncoding);
    }

    /**
     * <pre>
     * 发送不带参数的GET的HTTP请求
     * </pre>
     *
     * @param reqUrl
     *            HTTP请求URL
     * @return HTTP响应的字符串
     */
    public static String doGet(String reqUrl, String recvEncoding) {
        HttpURLConnection url_con = null;
        InputStream in = null;
        BufferedReader rd = null;
        String responseContent = null;
        try {

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");

            url_con.setConnectTimeout(HttpUtils.connectTimeOut);// （单位：毫秒）,连接超时
            url_con.setReadTimeout(HttpUtils.readTimeOut);// （单位：毫秒）,读操作超时

            url_con.setDoOutput(true);

            in = url_con.getInputStream();
            rd = new BufferedReader(new InputStreamReader(in, recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer temp = new StringBuffer();
            String crlf = System.getProperty("line.separator");
            while (tempLine != null) {
                temp.append(tempLine);
                temp.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = temp.toString();

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                rd.close();
                in.close();
            } catch (IOException e) {

            }

            if (url_con != null) {
                url_con.disconnect();
            }
        }

        return decodeUnicode(responseContent);
    }

    /**
     *
     * unicode 转换成 中文
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }




    /**
     * 通过post协议发送请求，并获取返回的响应结果
     * @param url   请求url
     * @param t     post传递的参数
     * @param headerMap  请求头
     * @param <T>
     * @return  返回服务器响应结果
     */
    public static  <T> String sendPostMethod(String url, T t,Map<String,String> headerMap) throws HttpException {
        String result = "";
        Map<String, Object> params = null;
        try {
            String s = JSON.toJSONString(t);
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            headerMap.entrySet().forEach(item->{ post.addHeader(item.getKey(),item.getValue());});
            RequestConfig config = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
            post.setConfig(config);
            post.setEntity(new StringEntity(s, "UTF-8"));
            HttpResponse response = client.execute(post);
            if (HTTP_STATUS_OK == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            } else {
                throw new HttpException("Invalide response from API: " + response.toString());
            }
        } catch (Exception e) {
            LOG.error("HTTP-POST请求异常信息：" + e.getMessage());
        }
        return result;
    }
}
