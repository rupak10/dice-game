package com.rupak.dice;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DiceGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiceGameApplication.class, args);
	}
	
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	//custom swagger configuration
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/v1/players/*").or (PathSelectors.ant("/api/v1/players")).or(PathSelectors.ant("/api/v1/game/*")))
				.apis(RequestHandlerSelectors.basePackage("com.rupak.dice"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Dice Game App",
				"Dice Game API for controlling game",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Rupak Kumar Das", "rupak.cse010@gmail.com", "https://github.com/rupak10/"),
				"API License",
				"https://github.com/rupak10/swagger-demo-app",
				Collections.emptyList());
	}
	

}

//html documentation path
//http://localhost:8080/swagger-ui/index.html

//json documentation path
//http://localhost:8080/v2/api-docs
