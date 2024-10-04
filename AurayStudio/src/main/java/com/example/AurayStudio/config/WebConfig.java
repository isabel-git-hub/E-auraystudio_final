package com.example.AurayStudio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.AurayStudio.com.MenuInterceptor;

@AutoConfiguration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private MenuInterceptor menu;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	}
}
