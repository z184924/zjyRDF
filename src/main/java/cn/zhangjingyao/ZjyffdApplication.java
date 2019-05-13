package cn.zhangjingyao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class ZjyffdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZjyffdApplication.class, args);
	}

}

