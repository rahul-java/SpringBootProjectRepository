package com.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

@Override
protected void configure(HttpSecurity http) throws Exception {
	
	http  
	      .authorizeRequests()
	      .antMatchers("/signin").permitAll()
	      .antMatchers("/public/**").hasRole("NORMAL")
	      .antMatchers("/users/**").hasRole("ADMIN")
	      .anyRequest()
	      .authenticated()
	      .and()
	      .formLogin()
	      .loginPage("/signin")
	      .loginProcessingUrl("/dologin")
	      .defaultSuccessUrl("/users/");
}

@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("rahul").password(this.passwordEncoder().encode("rahul")).roles("NORMAL");
		auth.inMemoryAuthentication().withUser("tom").password(this.passwordEncoder().encode("tom")).roles("ADMIN");
	}
	
@Bean
public PasswordEncoder passwordEncoder()
{
	return new BCryptPasswordEncoder(10);
}
	
}
