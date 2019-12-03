package com.malin.demo.common.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	
	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 
		//文件磁盘图片url 映射
		//配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
		 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/image/**").addResourceLocations("file:E:\\articleImages\\");
		registry.addResourceHandler("/userImage/**").addResourceLocations("file:E:\\userImages\\");
	  }
	/*
	 * static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE"
	 * };
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).
	 * allowedMethods(ORIGINS) .maxAge(3600); }
	 */
	 
	 
	 
	 	
	 
		
	 
}
