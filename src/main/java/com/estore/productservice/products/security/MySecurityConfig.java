package com.estore.productservice.products.security;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails user = User.withUsername("shruti").password(passwordEncoder().encode("shukla")).authorities("read")
				.build();
		userDetailsService.createUser(user);
		return userDetailsService;
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		// security.httpBasic(Customizer.withDefaults());
		// security.formLogin()
		security.formLogin(Customizer.withDefaults());
		security.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
		return security.build();

	}
}*/
