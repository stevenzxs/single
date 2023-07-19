package com.steven.biz.core.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
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

/**
 * 业务相关配置
 **/
@Configuration
public class BizConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     **/
    @Bean(value = "bizDocApi")
    public Docket bizDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("业务功能BIZ")
                        .description("业务功能BIZ")
                        .termsOfServiceUrl("https://www.stevenzxs.com")
                        .contact(new Contact("STEVEN_TEAM","https://www.stevenzxs.com", "stevenzxs@126.com"))
                        .version("2.0.0")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("业务功能BIZ")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.steven.biz"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("业务功能BIZ"));
    }
}
