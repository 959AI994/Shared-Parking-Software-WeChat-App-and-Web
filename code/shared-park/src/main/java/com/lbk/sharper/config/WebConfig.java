package com.lbk.sharper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: WJX
 * @Date: 2023/6/3 10:05
 * @Description 根据路径将磁盘上的文件映射为资源返回到前端
 * @Version 1.0
 */

@Configuration
public class WebConfig implements WebMvcConfigurer  {

    @Value("${prop.upload-folder}")
    private String filePath;


        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/**").addResourceLocations("file:"+filePath);
        }

}
