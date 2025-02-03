package com.carrental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //protect endpoint
        httpSecurity.authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/reservations/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(token -> token.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter())));
        //Cors filters
        httpSecurity.cors(Customizer.withDefaults());

        //content negotiation strategy
        httpSecurity.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());

        //we are not using cookies for session tracking >> disable CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

}
