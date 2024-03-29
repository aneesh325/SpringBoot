package com.spring.boot.test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import de.codecentric.boot.admin.config.EnableAdminServer;
/**
 * Hello world!
 *
 */
/*@RestController
@EnableAutoConfiguration
@EnableWebMvc
public class App 
{
	@RequestMapping("/hello")
	String home(){
		return "hello World!";
	}
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}*/

@EnableAdminServer
@Configuration
@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Configuration
	public static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
			http.logout().logoutUrl("/logout");
			http.csrf().disable();

			http.authorizeRequests().antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**").permitAll();
			http.authorizeRequests().antMatchers("/**").authenticated();

			http.httpBasic();
		}
	}

}