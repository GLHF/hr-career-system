package ru.kpfu.itis.hrcareersystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Инициализация Swagger
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
        //TODO если раскоментить, то падает исключение при запуске
//                .securitySchemes(newArrayList(oauth2()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API hr системы")
                .description("Описание API HR системы дипломной работы")
                .version("1.0")
                .build();
    }
}