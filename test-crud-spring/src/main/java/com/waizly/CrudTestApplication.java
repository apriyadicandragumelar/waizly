package com.waizly;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Project CRUD Waizly",
                                version = "1.0.0",
		                description = "Spring-Boot",
                  contact = @Contact(
                                name = "Mochamad Apriyadi Candra Gumelar",
                                url = "http://instagram/candra",
                                email = "candragumelar879@email.com"),
                  license = @License(
                                name = "waizly",
                                url = "http://waizly.com")))
public class CrudTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudTestApplication.class, args);
	}

	@Bean
	//mapped model
	public ModelMapper modelMapper() {
		return new ModelMapper();
	} 

}
