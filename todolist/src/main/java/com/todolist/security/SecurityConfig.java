package com.todolist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
		.disable()
		// AUTHORIZE
		.authorizeRequests()
			.mvcMatchers("/", "/signUp/**", "/schedule", "/loginForm/**")
				.permitAll()
			.mvcMatchers("/user/**")
				.hasRole("USER")
			.mvcMatchers("/admin/**")
				.hasRole("ADMIN")
			.anyRequest()
				.authenticated()
		.and()
		// EXCEPTION
		//.exceptionHandling()
		//	.authenticationEntryPoint(authenticationEntryPoint())
		//	.accessDeniedHandler(accessDeniedHandler())
		//.and()
		// LOGIN
		.formLogin()
			.loginProcessingUrl("/login")
				.usernameParameter("email")
				.passwordParameter("password")
			.loginPage("/loginForm")
			.defaultSuccessUrl("/", false)
			.failureUrl("/loginForm?error=true").permitAll()
			//.successHandler(authenticationSuccessHandler())
			//.failureHandler(authenticationFailureHandler())
		//.and()
		// LOGOUT
		//.logout()
			//.logoutUrl("/logout")
			//.logoutSuccessUrl("/home")
			//.invalidateHttpSession(true)
			//.deleteCookies("JSESSIONID")
			//.logoutSuccessHandler(logoutSuccessHandler())
			//.logoutSuccessUrl("/")
			//.addLogoutHandler(new CookieClearingLogoutHandler())
		//.and()
		// CSRF
		//.csrf()
		//	.disable()
			//.ignoringAntMatchers("/login")
			//.csrfTokenRepository(new CookieCsrfTokenRepository())
		;
	}
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	
	public void configureGlobal(AuthenticationManagerBuilder auth,
								@Qualifier("LoginUserDetailsService") UserDetailsService userDetailsService,
								PasswordEncoder passwordEncoder) throws Exception {
		auth.eraseCredentials(true)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
	}
	
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
	      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Bean
	 public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	      authenticationProvider.setUserDetailsService(userDetailsService);
	      authenticationProvider.setPasswordEncoder(passwordEncoder());
	      return authenticationProvider;
	}
	
	/*
	AuthenticationEntryPoint authenticationEntryPoint() {
		return new ToDoListAuthenticationEntryPoint();
	}
	
	AccessDeniedHandler accessDeniedHandler() {
		return new ToDoListAccessDeniedHandler();
	}
	
	AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new ToDoListAuthenticationSuccessHandler();
	}
	
	AuthenticationFailureHandler authenticationFailureHandler() {
		return new ToDoListAuthenticationFailureHandler();
	}
	
	LogoutSuccessHandler logoutSuccessHandler() {
        return new ToDoListLogoutSuccessHandler();
    }
	*/
}
