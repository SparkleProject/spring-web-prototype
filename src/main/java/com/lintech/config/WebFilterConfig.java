package com.lintech.config;

import jakarta.servlet.DispatcherType;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lintech.filter.UserFilter;

import java.util.EnumSet;

@Configuration
public class WebFilterConfig {

	@Bean
	public FilterRegistrationBean<ConfigurableSiteMeshFilter> sitemeshFilterRegistration() {
		FilterRegistrationBean<ConfigurableSiteMeshFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new ConfigurableSiteMeshFilter());
		registration.addUrlPatterns("/*");
		registration.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD));
		registration.setOrder(3);
		return registration;
	}

	@Bean
	public FilterRegistrationBean<UserFilter> userFilterRegistration() {
		FilterRegistrationBean<UserFilter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new UserFilter());
		registration.addUrlPatterns("/user/*");
		registration.setOrder(4);
		return registration;
	}
}
