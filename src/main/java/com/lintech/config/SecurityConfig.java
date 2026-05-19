package com.lintech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lintech.security.AdminUrlAuthorizationManager;
import com.lintech.security.AdminUserDetailsService;
import com.lintech.security.Md5PasswordEncoder;
import com.lintech.security.UserUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}

	@Bean
	@Order(1)
	public SecurityFilterChain adminFilterChain(HttpSecurity http,
			AdminUserDetailsService adminUserDetailsService,
			AdminUrlAuthorizationManager adminUrlAuthorizationManager,
			PasswordEncoder passwordEncoder) throws Exception {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(adminUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setHideUserNotFoundExceptions(false);

		http.securityMatcher("/admin/**")
			.authenticationProvider(provider)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/admin/login", "/admin/language/**", "/admin/assets/**", "/admin/error/**").permitAll()
				.requestMatchers("/admin/", "/admin/dashboard", "/admin/role-res/**").authenticated()
				.anyRequest().access(adminUrlAuthorizationManager)
			)
			.formLogin(form -> form
				.loginPage("/admin/login")
				.loginProcessingUrl("/admin/login")
				.defaultSuccessUrl("/admin/")
				.failureUrl("/admin/login?error")
			)
			.logout(logout -> logout
				.logoutUrl("/admin/logout")
				.logoutSuccessUrl("/admin/login")
			)
			.rememberMe(rm -> rm
				.key("adminRememberMeKey")
				.tokenValiditySeconds(604800)
				.rememberMeCookieName("arm")
			)
			.csrf(csrf -> csrf.disable());

		return http.build();
	}

	@Bean
	@Order(2)
	public SecurityFilterChain userFilterChain(HttpSecurity http,
			UserUserDetailsService userUserDetailsService,
			PasswordEncoder passwordEncoder) throws Exception {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		provider.setHideUserNotFoundExceptions(false);

		http.securityMatcher("/user/**", "/login")
			.authenticationProvider(provider)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/login", "/assets/**", "/user/static/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/user/index")
				.failureUrl("/login?error")
			)
			.logout(logout -> logout
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/login")
			)
			.rememberMe(rm -> rm
				.key("userRememberMeKey")
				.tokenValiditySeconds(604800)
				.rememberMeCookieName("urm")
			)
			.csrf(csrf -> csrf.disable());

		return http.build();
	}

	@Bean
	@Order(3)
	public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.anyRequest().permitAll()
			)
			.csrf(csrf -> csrf.disable());
		return http.build();
	}
}
