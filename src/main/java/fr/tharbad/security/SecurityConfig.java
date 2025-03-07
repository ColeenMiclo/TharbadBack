package fr.tharbad.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.tharbad.security.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity //Web security is enabled
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf( csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz.requestMatchers(HttpMethod.GET, "/api/public/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll() 
                    .requestMatchers(HttpMethod.GET, "/api/user/**").hasAnyAuthority("USER", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated());
        
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
}
