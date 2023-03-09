package com.blog.project.springbootwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class Application {//main class

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);//내장WAS 실행-톰캣설치X,Jar로 실행
	}
}
