package com.example.demo.securitypackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails ajay = User.builder()
                .username("ajaypaul")
                .password("{noop}ajay123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ajay);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer.anyRequest().authenticated())
                .formLogin(form ->
                        form.loginPage("/login")
                                .loginProcessingUrl("/processlogin")
                                .permitAll()
                                .defaultSuccessUrl("/tutorial/new", true)
                );
        return http.build();
    }
}