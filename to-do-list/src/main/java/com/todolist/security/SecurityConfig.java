package com.todolist.security;

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

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.headers().cacheControl().disable().and()
		.csrf()
		.disable()
		// AUTHORIZE
		.authorizeRequests()
			.mvcMatchers("/", "/signup/**", "/hello/**", "/loginForm/**")
				.permitAll()
			.mvcMatchers("/user/**")
				.hasRole("USER")
			.mvcMatchers("/admin/**")
				.hasRole("ADMIN")
			.anyRequest()
				.authenticated()
		.and()
		// EXCEPTION
		.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint())
			.accessDeniedHandler(accessDeniedHandler())
		.and()
		// LOGIN
		.formLogin()
			.loginProcessingUrl("/login")
				.usernameParameter("email")
				.passwordParameter("password")
			.loginPage("/loginForm")
			//.defaultSuccessUrl("/home", false)
			//.failureUrl("/loginForm?error=true").permitAll()
			.successHandler(authenticationSuccessHandler())
			.failureHandler(authenticationFailureHandler())
		.and()
		// LOGOUT
		.logout()
			.logoutUrl("/logout")
			//.logoutSuccessUrl("/home")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessHandler(logoutSuccessHandler())
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
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth,
								@Qualifier("toDoListUserDetailsService") UserDetailsService userDetailsService,
								PasswordEncoder passwordEncoder) throws Exception {
		auth.eraseCredentials(true)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
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
	
}
