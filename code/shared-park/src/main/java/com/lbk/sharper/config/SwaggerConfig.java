package com.lbk.sharper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author LBK
 * @Date 2021-05-03
 * @Description 配置Swagger接口文档
 *
 */
@Component
@Slf4j
@Configuration //在springboot中加载配置文件
/**
    * 加载swagger
    * @author LBK
* */
@EnableSwagger2
public class SwaggerConfig implements ApplicationListener<WebServerInitializedEvent> {

    /**
     * 定义服务器端口
     */
    private int serverPort;

    public int getPort() {
        return this.serverPort;
    }

    /**
     * @author LBK
     * @Description 控制台输出 Swagger 接口文档地址
     **/

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            InetAddress inetAddress = Inet4Address.getLocalHost();
            this.serverPort = event.getWebServer().getPort();
            log.info("项目启动启动成功！接口文档地址: http://"+inetAddress.getHostAddress()+":"+serverPort+"/swagger-ui.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 调用apiInfo方法
                .apiInfo(apiInfo())
                //配置访问路径
                .pathMapping("/")
                .select()
                //匹配路径下的方法
                .paths(PathSelectors.regex("/.*"))
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("")
                .contact(new Contact("myCode","www.baidu.com", "2305975309@qq.com"))
                .description("springBoot-sharedPark共享停车项目生成swagger-ui接口文档")
                .version("1.0.0.0")
                .build();
    }

}