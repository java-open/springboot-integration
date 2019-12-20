package com.tysh.aop.bean;

import com.tysh.aop.aop.Dot;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InnerDemoBean {

    @Dot
    public String print() {
        try {
            System.out.println("in innerDemoBean start!");
            String rans = System.currentTimeMillis() + "|" + UUID.randomUUID();
            System.out.println(rans);
            return rans;
        } finally {
            System.out.println("in innerDemoBean over!");
        }
    }
}