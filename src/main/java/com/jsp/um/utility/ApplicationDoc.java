package com.jsp.um.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDoc {
	
	@Bean
	
	public OpenAPI openAPI() {
		return new OpenAPI().info(new Info()
				.title("User_ManagementSystem")
				.version("v1")
				.description("Spring boot project built using **RESTFUL** aRCHITECTURE"
						+"- crud operation performed\n"
						
						+"- pubic class operation\n"));
	}
	

}
