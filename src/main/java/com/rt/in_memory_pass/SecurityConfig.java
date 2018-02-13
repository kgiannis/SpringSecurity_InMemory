package com.rt.in_memory_pass;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * InMemory Security.
	 * Create specific user/pass accounts and roles
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ADMIN")
			.and()
			.withUser("user").password("user").roles("USER")
			.and()
			.withUser("adus").password("adus").roles("ADMIN","USER");
	}
	
	
	/**
	 * Authorize Requests.
	 * Paths: '/' available for all
	 * Paths: '/admin' and 'admin/**' only for ADMIN role
	 * Paths: '/user' and 'user/**' only for USER role
	 * Paths: '/adus' and 'adus/**' only for ADMIN or USER roles
	 * Login / Logout available for all
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/")
				.permitAll()
			.antMatchers("/admin", "/admin/*")
				.hasRole("ADMIN")
			.antMatchers("/user", "/user/*")
				.hasRole("USER")
			.antMatchers("/adus")
				.hasAnyRole("ADMIN","USER")
			.anyRequest()
				.authenticated()
			.and().formLogin()
				.permitAll()
			.and().logout()
				.permitAll();
	}

}
