package io.codelex.flightplanner.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable() //disables CSRF protection
                .authorizeRequests() //tells that path matchers will start
                .antMatchers("/api/**")
                .anonymous()
                .antMatchers("/testing-api/**")
                .anonymous()//everything under /spring without authentication
                .anyRequest()
                .authenticated() //everything else with authentication
                .and()
                .httpBasic(); //authentication type

        return http.build();


    }
}


