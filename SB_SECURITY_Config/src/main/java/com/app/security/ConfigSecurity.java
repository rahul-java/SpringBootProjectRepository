package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private BCryptPasswordEncoder encoder;  
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Authentication- InMemory,JDBC,ORM
		
		auth.inMemoryAuthentication().withUser("Abhishek").password("{noop}abhi").authorities("user");
		auth.inMemoryAuthentication().withUser("Rahul").password("{noop}rahul").authorities("admin");
		auth.inMemoryAuthentication().withUser("Tamhid").password("{noop}tamhid").authorities("manager");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Authorization- permitAll,hasAuthority,hasAnyAuthority,authenticated
	
		http.authorizeRequests().antMatchers("/welcome").permitAll()
		.antMatchers("/viewBalance").hasAnyAuthority("user","manager")
		.antMatchers("/home").authenticated()
		.antMatchers("/activateUser").hasAuthority("admin")
		.and()
		.formLogin()
		.defaultSuccessUrl("/home",true);
	}
}
