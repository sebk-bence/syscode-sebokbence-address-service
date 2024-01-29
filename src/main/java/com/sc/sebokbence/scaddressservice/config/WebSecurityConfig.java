package com.sc.sebokbence.scaddressservice.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
    httpSecurity
        .authorizeHttpRequests(
            (authorize) -> authorize.anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user = User
        .builder()
        .username("user1")
        .password(passwordEncoder().encode("password1"))
        .roles("ADDRESS_READ")
        .build();
    return new InMemoryUserDetailsManager(user);
  }
}
