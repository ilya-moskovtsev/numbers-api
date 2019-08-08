package org.nameapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value(value = "${numbers.http.auth-token-header-name}")
    private String principalRequestHeader;

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .apis(RequestHandlerSelectors.basePackage("org.nameapi"))
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(List.of(apiKey()))
                .securityContexts(List.of(securityContext()));
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Numbers API",
                "You can offer numbers. You can get stats that contains 3 things: 1) the smallest number encountered so far. 2) the largest number encountered so far. 3) the average of all numbers encountered so far",
                "0.1",
                "Free to use",
                new Contact("Ilya Moskovtsev", "https://github.com/ilya-moskovtsev", "ilya.moskovtsev@gmail.com"),
                "API License",
                "https://github.com/ilya-moskovtsev",
                Collections.emptyList()
        );
    }

    private ApiKey apiKey() {
        return new ApiKey("mykey", principalRequestHeader, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("mykey", authorizationScopes));
    }
}
