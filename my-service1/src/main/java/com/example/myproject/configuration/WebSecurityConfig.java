package com.example.myproject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends ResourceServerConfigurerAdapter {
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
//
//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
//	}
//
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests()
                .antMatchers("/login**").permitAll()
                .anyRequest().authenticated();
	}
//
//	private PasswordEncoder getPasswordEncoder() {
//		return new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence charSequence) {
//				return charSequence.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence charSequence, String s) {
//				return true;
//			}
//		};
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
