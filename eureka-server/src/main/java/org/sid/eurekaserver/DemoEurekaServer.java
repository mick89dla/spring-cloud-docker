package org.sid.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoEurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(DemoEurekaServer.class, args);
	}

}
