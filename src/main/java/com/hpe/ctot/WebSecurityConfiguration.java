package com.hpe.ctot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(WebSecurityConfiguration.class);
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		log.info("WebSecurityConfiguration.configure(HttpSecurity http)");
		http.csrf().disable()
        	.antMatcher("/**")
        	.authorizeRequests()
//        		.antMatchers("/approval/**").hasRole("MANAGER")
//        		.antMatchers("/tracker/**").hasRole("USER")
//            	.anyRequest().hasAnyRole("MANAGER", "USER")
        		.antMatchers("/**").hasAnyAuthority("MANAGER", "USER")
            	.and()
            .httpBasic();
//        http.authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .and()
//            .httpBasic();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService);
			//.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		log.info("WebSecurityConfiguration.passwordEncoder()");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
