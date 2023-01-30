package com.LC.LaraCulturaCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.*;


@SpringBootApplication
@EnableJpaRepositories("com.LC.LaraCulturaCommunity.repository")
@EntityScan("com.LC.LaraculturaCommunity..model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class LaraCulturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaraCulturaApplication.class, args);
	}

}
