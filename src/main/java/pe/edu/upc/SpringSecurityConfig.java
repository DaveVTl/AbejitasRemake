package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.serviceimplement.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/css/**", "/js/**", "/img/**", "/login/**",
						"/freelancers/new", "/freelancers/save", "/mype/new", "/mype/save", "/freelancers/prere")
				.permitAll().anyRequest().authenticated().and().formLogin().permitAll().loginPage("/login").permitAll()
				.and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/error");
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {

		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
}
