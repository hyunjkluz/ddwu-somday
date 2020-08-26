/**
 * 
 */
package com.somday.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.amazonaws.services.iotsitewise.model.Property;
import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Since : Aug 24, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 24, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
@Configuration
@EnableSwagger2
@Profile({ "local", "dev" })
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
@PropertySource("classpath:config/application.properties")
public class SpringFoxConfig extends WebMvcConfigurerAdapter {
	@Bean
	public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiEndPointsInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .useDefaultResponseMessages(false)                                   
                .globalResponseMessage(RequestMethod.GET,                     
                  Lists.newArrayList(
                		  new ResponseMessageBuilder()
                		  .code(500)
                		  .message("INTERNAL SERVER ERROR & DB ERROR")
                		  .responseModel(new ModelRef("Error"))
                		  .build(),
                    new ResponseMessageBuilder() 
                      .code(401)
                      .message("UNAUTHORIZED")
                      .build(),
                      new ResponseMessageBuilder()
            		  .code(400)
            		  .message("BAD REQUEST")
            		  .responseModel(new ModelRef("Error"))
            		  .build()));

    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer +accessToken", "Authorization", "header");
    }

	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("SOMDAY REST API")
				.description("This documents describes about decide version 7 REST API")
				.contact(new Contact("Somday", PropertyUtil.getProperty("somday.official.help.mail"), PropertyUtil.getProperty("somday.official.email"))).version("v1")
				.build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	private springfox.documentation.spi.service.contexts.SecurityContext securityContext() { 
		return springfox.documentation.spi.service.contexts.SecurityContext
				.builder() 
			.securityReferences(defaultAuth()) 
			.forPaths(PathSelectors.any()) 
			.build(); 
	}

	List<SecurityReference> defaultAuth() { 
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; authorizationScopes[0] = authorizationScope; 
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes)); 
	}

//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//            .clientId(CLIENT_ID)
//            .clientSecret(CLIENT_SECRET)
//            .scopeSeparator(" ")
//            .useBasicAuthenticationWithAccessCodeGrant(true)
//            .build();
//    }
//    
//    private SecurityScheme securityScheme() {
//        GrantType grantType = new AuthorizationCodeGrantBuilder()
//            .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
//            .tokenRequestEndpoint(
//              new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_SECRET))
//            .build();
//     
//        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
//            .grantTypes(Arrays.asList(grantType))
//            .scopes(Arrays.asList(scopes()))
//            .build();
//        return oauth;
//    }
//    
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//          .securityReferences(
//            Arrays.asList(new SecurityReference("spring_oauth", scopes())))
//          .forPaths(PathSelectors.regex("/foos.*"))
//          .build();
//    }
//    
//    private AuthorizationScope[] scopes() {
//        AuthorizationScope[] scopes = { 
//          new AuthorizationScope("read", "for read operations"), 
//          new AuthorizationScope("write", "for write operations"), 
//          new AuthorizationScope("foo", "Access foo API") };
//        return scopes;
//    }
}
