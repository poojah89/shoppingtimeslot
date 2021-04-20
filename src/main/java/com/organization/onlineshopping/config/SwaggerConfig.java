package com.organization.onlineshopping.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@ComponentScan({ "com.organization.onlineshopping" })

public class SwaggerConfig implements WebMvcConfigurer {

	private static final String API_KEY = "apiKey";
	Contact contact1 = new Contact().name("Pooja").email("poojah89@gmail.com").url("https://www.stylingthenation.com/");
	License license1 = new License().name("Apache 1.0").url("");

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components().addSecuritySchemes(API_KEY, apiKeySecuritySchema())) // define
																												// the
																												// apiKey
																												// SecuritySchema
				.info(new Info().title("Online Shopping")
						.description("API for Online Shopping").contact(contact1)
						.license(license1))
				.security(Collections.singletonList(new SecurityRequirement().addList(API_KEY))); // then apply it. If
																									// you don't apply
																									// it will not be
																									// added to the
																									// header in cURL
	}

	public SecurityScheme apiKeySecuritySchema() {
		return new SecurityScheme().name("Authorization") // authorization-token

				.description("Description about the TOKEN").in(SecurityScheme.In.HEADER)
				.type(SecurityScheme.Type.APIKEY);
	}

}