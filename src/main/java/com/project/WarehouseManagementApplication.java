package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EntityScan(basePackages={"com.project.pojo"})
//@EnableSwagger2
public class WarehouseManagementApplication /* implements WebMvcConfigurer */ {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseManagementApplication.class, args);
	}
	/*
	 * public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").
	 * allowedHeaders("*"); }
	 */
	/*@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.project"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}
	
	public ApiInfo apiInfo() {
		ApiInfoBuilder builder = new ApiInfoBuilder();
		builder.title("Distributor Management");
		builder.description("Api Documentation");
		builder.version("1.1");
		return builder.build();
	}*/

}
