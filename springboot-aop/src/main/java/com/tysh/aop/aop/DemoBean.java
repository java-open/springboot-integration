package com.tysh.aop.aop;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 */
@Component
public class DemoBean {

    public String randUUID(long time){
        try {
            System.out.println("randUUID start");
            return genUUID(time);
        } finally {
            System.out.println("randUUID finally");

        }
    }


    //通过注解引入切面
    @Dot
    public String genUUID(long time) {
        System.out.println("before process---");
        try {
            return UUID.randomUUID() + "|" + time;
        } finally {
            System.out.println("process finally-----");
        }
    }

}
