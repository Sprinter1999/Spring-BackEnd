package cn.edu.bupt.ch5_3.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfiguration {
    @Bean
    public Docket createDemoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("MyBatis Plus Demo")
                        .description("MyBatis Plus Demo接口文档")
                        .version("1.0")
                        .build())//设置API基本信息
                .select()// 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("cn.edu.bupt.ch5_3")) // 设置对哪些包内的api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .groupName("MyBatis Plus Demo");
    }
    @Bean
    public Docket createUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("User related APIs")
                        .description("利用自定义响应消息格式表示用户相关操作结果")
                        .version("1.0")
                        .build())//设置API基本信息
                .select()// 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("cn.edu.bupt.ch5_3")) // 设置对哪些包内的api进行监控
                .paths(PathSelectors.ant("/v1/users/**")) // 仅监控路径/api/v1/**
                .build()
                .groupName("User Related API");

    }

    @Bean
    public Docket createBlogApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Blog related APIs")
                        .description("利用自定义响应消息格式表示用户的动态相关操作结果")
                        .version("1.0")
                        .build())//设置API基本信息
                .select()// 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("cn.edu.bupt.ch5_3")) // 设置对哪些包内的api进行监控
                .paths(PathSelectors.ant("/v1/blogs/**")) // 仅监控路径/api/v1/**
                .build()
                .groupName("Blog Related API");

    }
}
