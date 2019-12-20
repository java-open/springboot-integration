package com.tysh.aop;

import com.tysh.aop.aop.DemoBean;
import com.tysh.aop.bean.InnerDemoBean;
import com.tysh.aop.demo.AnoDemo;
import com.tysh.aop.demo.PrintDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAopApplication {

	/**
	 * 测试aop获取日志
	 */

	public SpringbootAopApplication(PrintDemo printDemo, AnoDemo anoDemo) {
		System.out.println(printDemo.genRand(10, "--天山云海"));
		System.out.println(anoDemo.gen("123"));
	}


/*
	*/
/**
	 * 测试
	 *//*

	private InnerDemoBean innerDemoBean;

	public SpringbootAopApplication(InnerDemoBean innerDemoBean) {
		this.innerDemoBean = innerDemoBean;
		this.innerDemoBean();
	}

	private void innerDemoBean() {
		System.out.println("result: " + innerDemoBean.print());
	}
*/

/*
	private DemoBean demoBean;

	public SpringbootAopApplication(DemoBean demoBean) {
		this.demoBean = demoBean;
		this.demoBean();
	}

	//测试调用方法不满足拦截规则,调用本类中其他满足拦截条件的方法 不会被拦截
	*//*private  void demoBean(){
		System.out.println("----->"+demoBean.randUUID(System.currentTimeMillis()));

	}*//*

	private  void demoBean(){
		System.out.println("----->"+demoBean.genUUID(System.currentTimeMillis()));

	}*/

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAopApplication.class, args);
	}

}
