package ca.sheridancollege.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();//remove in deployment
		http.headers().frameOptions().disable();//remove in deployment
		http.authorizeRequests()
		
			//Define URL's and who has access
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/h2-console/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/register").permitAll()
			.antMatchers("/",
						"/images/**",
						"/css/**",
						"/js/**",
						"/fragments/**",
						"/**").permitAll()
			//.antMatchers("/h2-console/**").permitAll()//remove in deployment
			
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/user", true)
			.permitAll()
			.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll()
			.and()
			.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Autowired
	UserDetailedServiceImple userDetailsService;
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	throws Exception{
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
		
	}

}
