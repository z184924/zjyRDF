package cn.zhangjingyao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class})
public class ZjyRdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZjyRdfApplication.class, args);
	}

}

