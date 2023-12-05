package com.example.basfchallenge;

import com.example.basfchallenge.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class BasfChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasfChallengeApplication.class, args);
	}

}
