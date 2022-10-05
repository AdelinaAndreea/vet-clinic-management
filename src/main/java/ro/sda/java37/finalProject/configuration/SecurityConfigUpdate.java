package ro.sda.java37.finalProject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static ro.sda.java37.finalProject.configuration.RequestInterceptor.APP_TOKEN_KEY;

@Configuration
@EnableWebSecurity
public class SecurityConfigUpdate {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests((authz) -> authz.antMatchers(HttpMethod.GET, "/api")
        .hasAnyRole("ADMIN", "USER")
        .anyRequest()
        .permitAll())
      .formLogin().and()
      .httpBasic().and()
      .logout()
      .and()
      .csrf().ignoringAntMatchers("/api/**")
      .and()
      .cors()
      .and()
      .headers().frameOptions().disable()
    ;
    return http.build();
  }

  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails admin = User.withDefaultPasswordEncoder()
      .username("admin")
      .password("Secret_123")
      .roles("ADMIN")
      .build();
    return new InMemoryUserDetailsManager(admin);
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService())
      .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(Arrays.asList("content-type", APP_TOKEN_KEY));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
