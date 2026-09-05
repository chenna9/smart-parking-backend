package org.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartParkingApplication {

	public static void main(String[] args) {
		 System.out.println(">>> DB_URL=[" + System.getenv("DB_URL") + "]");
		 System.out.println(">>> DB_USERNAME=[" + System.getenv("DB_USERNAME") + "]");
		SpringApplication.run(SmartParkingApplication.class, args);
	}

}
