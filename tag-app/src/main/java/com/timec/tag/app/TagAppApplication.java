package com.timec.tag.app;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TagAppApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TagAppApplication.class);
		application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
		SpringApplication.run(TagAppApplication.class, args);
	}
}
