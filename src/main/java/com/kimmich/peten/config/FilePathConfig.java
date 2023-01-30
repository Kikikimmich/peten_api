package com.kimmich.peten.config;

import com.kimmich.peten.emun.PathConst;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilePathConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**") //虚拟url路径
                .addResourceLocations("file:" + PathConst.IMAGE_ROOT); //真实本地路径
    }
}
