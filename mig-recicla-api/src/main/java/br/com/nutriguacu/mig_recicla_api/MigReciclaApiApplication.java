package br.com.nutriguacu.mig_recicla_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MigReciclaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MigReciclaApiApplication.class, args);
	}

}
