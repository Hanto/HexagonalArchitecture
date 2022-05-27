package com.myrran.cleanarchitecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableDiscoveryClient @EnableEurekaClient
@EnableRetry
public class CleanArchitectureApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CleanArchitectureApplication.class, args);
	}

}