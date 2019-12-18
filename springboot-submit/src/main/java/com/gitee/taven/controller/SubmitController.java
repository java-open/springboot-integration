package com.gitee.taven.controller;

import com.gitee.taven.ApiResult;
import com.gitee.taven.aop.NoRepeatSubmit;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *@Description 模拟业务重复请求
 *@author
 *@Date 11:10 2019/12/16
 *@returen
 **/
@RestController
public class SubmitController {

    @PostMapping("submit")
    @NoRepeatSubmit(lockTime = 10)
    public Object submit(@RequestBody UserBean userBean) {
        try {
            System.out.println("------------------>"+userBean.toString());
            // 模拟业务场景
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ApiResult(200, "成功", userBean.userId);
    }

    public static class UserBean {
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId == null ? null : userId.trim();
        }
    }



}
