package com.app.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/viewBalance").hasAnyAuthority("ADMIN","USER","MANAGER")
		.antMatchers("/activateUser").authenticated()
		.antMatchers("/user/","/user/save").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.defaultSuccessUrl("/profile",true)
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		;
		
		
		
	}
}
