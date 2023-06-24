package cn.apkr;

import cn.apkr.config.SpringConfig;
import cn.apkr.service.UserService;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {
	public static void main(String[] args) {
		UserService userService = (UserService) SpringConfig.ctx.getBean("userService");
		System.out.println(userService.signIn("test2", "123456"));


	}
}
