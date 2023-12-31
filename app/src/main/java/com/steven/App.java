/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.steven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Slf4j
@RestController
@EnableSwagger2WebMvc
@SpringBootApplication
@ComponentScan("com.steven.**")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class App {
    public static void main(String[] args) {
        SpringApplication SpringApplication = new SpringApplication(App.class);
        SpringApplication.run(args);
        log.info(">>> {}", App.class.getSimpleName().toUpperCase() + " STARTING SUCCESS");
    }
}
