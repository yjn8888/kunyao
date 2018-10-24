package com.kunyao.dubbo.boot.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		synchronized (Main.class) {
//			while (true) {
//				try {
//					Main.class.wait();
//				} catch (Throwable e) {
//
//				}
//			}
		}
	}
}
