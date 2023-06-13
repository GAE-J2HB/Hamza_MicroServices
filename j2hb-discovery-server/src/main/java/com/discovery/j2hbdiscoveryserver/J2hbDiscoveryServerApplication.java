package com.discovery.j2hbdiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class J2hbDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(J2hbDiscoveryServerApplication.class, args);
	}

}
