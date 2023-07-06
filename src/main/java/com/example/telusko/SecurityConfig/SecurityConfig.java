package com.example.telusko.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.example.telusko.Filter.JwtAuthFilter;

@Component
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authfilter;

	@Bean
	// Authentication
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("User/delete/{id}", "/updateProductQuantity", "/findAllProducts",
						"/findproduct/{productName}", "/User/authentication", "/User/put/edit", "/User/addUser",
						"/{id}", "/User/get/All","/Sold/products")
				.permitAll()
				// "/User/get/All","/User/delete/{id}","/User/addUser" for error i have added as
				// i had forgotten the password
				.and().authorizeHttpRequests().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authfilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public UserDetailsService userDetailsService() {

		// this method is basically hardcoded u have to harcode the username password
		// its not directly collecting from db so we have created a method which will
		// collect the data from db and will verify
//		UserDetails admin=User.withUsername("Akhil")
//				.password(encoder.encode("Ak1234"))
//				.roles("ADMIN")
//				.build();
//		UserDetails guest=User.withUsername("Harshil")
//				.password(encoder.encode("Ha1234"))
//				.roles("GUEST")
//				.build();
//		return new InMemoryUserDetailsManager(admin,guest);

		return new UserInfoUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
