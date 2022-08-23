package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.domain.repository.NoRepositoryBean.CustomJpaRepositoryImlp;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImlp.class)
public class AlgafoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApplication.class, args);
	}

}
