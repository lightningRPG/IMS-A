package cn.apkr.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConfig {
	public static ClassPathXmlApplicationContext ctx;

	static {
		ctx = new ClassPathXmlApplicationContext("./springConf.xml");
	}

}
