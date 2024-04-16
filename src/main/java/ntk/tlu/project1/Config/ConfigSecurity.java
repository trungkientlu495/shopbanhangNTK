package ntk.tlu.project1.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {
	private static final Logger logger = LoggerFactory.getLogger(ConfigSecurity.class);
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	public void config(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(12));
	}
	
//	@Autowired
//	public void config(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("nguyentrungk495@gmail.com").password("{noop}Kien@123").authorities("User");
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,HttpSession session) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/Admin/**").hasAuthority("Admin")
	            .requestMatchers("/User/**").hasAuthority("User")
	            .anyRequest().permitAll()
	        )
	        .formLogin( (formLogin) -> 
            formLogin 
                    .loginPage("/api/login") 
                    //.loginProcessingUrl("/api/login")
                    .usernameParameter("email") 
                    .passwordParameter("password") 
                    .defaultSuccessUrl("/api/register")
                    .successHandler((request, response, authentication) -> {
                    	if(authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("User"))) {
                    		// Lấy email của người dùng từ thông tin xác thực
                            String email = authentication.getName();
                            logger.info("Email: "+email);
                            // Lưu email vào phiên
                            session.setAttribute("email", email);
                            // Chuyển hướng người dùng đến trang mặc định
                            response.sendRedirect("/User/home");
                    	}
                    	if(authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("Admin"))) {
                    		response.sendRedirect("/Admin/dashboard");
                    	}
                    })
	        )
	        .httpBasic(Customizer.withDefaults());
//	        .logout(logout -> logout
//	            .permitAll());
	    
	    return http.build();
	}
	
	
}
