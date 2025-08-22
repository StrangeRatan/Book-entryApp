package com.Ratan.my_fist_Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication //Marks this class as a Spring Boot application
@EnableTransactionManagement
public class MyFistDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFistDemoApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
