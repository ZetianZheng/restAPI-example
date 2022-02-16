package com.udacity.RestAPIexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Swagger configuration class: Reference: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
 *      - The configuration of Swagger mainly centers around the Docket bean:
 *      - After defining the Docket bean, its select() method returns an instance of ApiSelectorBuilder,
 *      which provides a way to control the endpoints exposed by Swagger.
 *      - We can configure predicates for selecting RequestHandlers with the help of RequestHandlerSelectors and PathSelectors.
 *      Using any() for both will make documentation for our entire API available through Swagger.
 *
 * [(58条消息) swagger配置扫描接口、扫描路径条件_Sky_for_me的博客-CSDN博客_swagger扫描](https://blog.csdn.net/Sky_for_me/article/details/107585852)
 * PathSelectors 可使用的 mapperHandler 路径匹配的方法：扫描匹配成功的路径，生成API
 *    any() --- 匹配所有的路径
 *    ant() --- 匹配传入参数的路径
 *    regex() --- 通过正则表达式匹配路径
 *
 *
 * RequestHandlerSelectors 可使用的扫描条件：
 *    basePackage() --- 只扫描指定路径上的类
 *    any() --- 扫描所有类
 *    withClassAnnotation() --- 通过判断类上的注解中有xxx注解扫描类
 *    withMethodAnnotation() --- 通过判断方法上的注解中有xxx注解扫描方法
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Dog REST API",
                "This API returns a list of dogs.",
                "1.0",
                "http://www.udacity.com/tos",
                new Contact("Zane", "www.udacity.com", "myeaddress@udacity.com"),
                "License of API", "http://www.udacity.com/license", Collections.emptyList());
    }
}
