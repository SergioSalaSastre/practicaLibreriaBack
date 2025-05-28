package com.fullStackHexagonal.fullstackHexagonal.infraestructure.ApiRest.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	 @Bean
	    public OpenAPI apiInfo() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("Book API")
	                        .description("Documentación de la API para gestión de libros")
	                        .version("1.0"));
	    }
}
