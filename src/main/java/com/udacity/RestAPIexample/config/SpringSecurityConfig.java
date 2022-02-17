package com.udacity.RestAPIexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Configuration
 *      指示一个类声明一个或多个@Bean方法，并且可以由Spring容器处理，以便在运行时为这些bean生成BeanDefinition和服务请求
 *      Annotating a class with the @Configuration indicates that the class can be used by the Spring IoC container as **a source of bean definitions**.
 *      The @Bean annotation tells Spring that a method annotated with @Bean will return an object that should be registered as a bean in the Spring application context.
 *
 * extends WebSecurityConfigurerAdapter:(Spring security)
 *      the class should extend WebSecurityConfigurerAdapter to secure your API with Basic Authentication
 *
 * .csrf().disable()
 *      CSRF – Cross-Site Request Forgery attack
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("password"))
                .roles("USER");
    }
    /**
     * 2.1.6-RELEASE of Spring Boot:
     *
     * 2.2.x+ Spring Boot has a good practice:
     *      that you can't put in the same class the @Bean
     *      and make the @Autowired of a method or property
     *      that *will use* this @Bean to avoid conflicts.
     *
     *      So to solve this error we will:
     *
     *      Create the EncoderConfig class
     *      We will include the @Configuration annotation in the EncoderConfig class
     *      We will move the @Bean for PasswordEncoder to EncoderConfig class
     *      In SpringSecurityConfig we will put PasswordEncoder property and in it we will use @Autowired annotation
     *      In the configureGlobal method we change the encoder() to encoder
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
