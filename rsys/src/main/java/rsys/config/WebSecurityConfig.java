package rsys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import rsys.domain.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/admin","/admin/**").hasRole("ADMIN")//ADMINしか入れない
                .antMatchers("/**").hasRole("USER")//USERしか入れない -> ADMINの場合、ADMINとUSERの両方を設定する UserDetailsのgetAuthorities()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	/*auth
    	.userDetailsService(userDetailsServiceImpl)
    	.passwordEncoder(passwordEncoder());*/
    	System.out.println("security configure");
        auth
            .inMemoryAuthentication()
                .withUser("Miyamoto").password("{noop}xxx").roles("ADMIN");

    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
          return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
         authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
         authenticationProvider.setPasswordEncoder(passwordEncoder());
         return authenticationProvider;
    }

    /*
    @SuppressWarnings("deprecation")
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    */

}
