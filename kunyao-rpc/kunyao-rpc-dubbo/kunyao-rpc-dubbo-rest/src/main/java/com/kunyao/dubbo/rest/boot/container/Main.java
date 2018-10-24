package com.kunyao.dubbo.rest.boot.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Main.class);
		springApplication.setWebEnvironment(false);
		springApplication.run(args);
//		synchronized (Main.class) {
//			while (true) {
//				try {
//					Main.class.wait();
//				} catch (Throwable e) {
//
//				}
//			}
//		}
	}
}
