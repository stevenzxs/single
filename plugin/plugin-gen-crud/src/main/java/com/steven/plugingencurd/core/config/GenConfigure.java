package com.steven.plugingencurd.core.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import com.steven.common.pojo.CommonResult;

import javax.annotation.Resource;

@Configuration
public class GenConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     **/
    @Bean(value = "genDocApi")
    public Docket sysDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("代码生成GEN")
                        .description("代码生成GEN")
                        .termsOfServiceUrl("http://www.stevenzxs.com")
                        .contact(new Contact("STEVEN_TEAM","http://www.stevenzxs.com", "stevenzxs@126.com"))
                        .version("0.0.1")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("代码生成GEN")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.steven.plugingencurd"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("代码生成GEN"));
    }
}
