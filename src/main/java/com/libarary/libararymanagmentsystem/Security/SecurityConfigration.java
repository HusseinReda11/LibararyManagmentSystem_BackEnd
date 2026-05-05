package com.libarary.libararymanagmentsystem.Security;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigration {

    private final UserDetailsServicee userDetailsServicee;

    public SecurityConfigration(UserDetailsServicee userDetailsServicee) {
        this.userDetailsServicee = userDetailsServicee;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()


                        .requestMatchers(HttpMethod.POST,   "/books/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,    "/books/update/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/delete/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/users/").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/users/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,    "/users/update/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/delete/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT,    "/users/role/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/borrow/overdue").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET,  "/books").authenticated()
                        .requestMatchers(HttpMethod.GET,  "/books/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/borrow/add").authenticated()
                        .requestMatchers(HttpMethod.PUT,  "/borrow/return/**").authenticated()
                        .requestMatchers(HttpMethod.GET,  "/borrow/user/**").authenticated()
                        .requestMatchers(HttpMethod.GET,  "/borrow/active").authenticated()
                        .requestMatchers(HttpMethod.GET,  "/borrow/**").authenticated()

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Throwable {
        return config.getAuthenticationManager();
    }
}