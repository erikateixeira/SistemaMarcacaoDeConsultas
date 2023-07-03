package com.saper.sistemadeconsultas.config.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* */
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET, "/funcionario/nome").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/funcionario/dados").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/funcionario/username").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.POST, "/funcionario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/funcionario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/funcionario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/medico/nome").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/medico/dados").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/medico/username").hasRole("MEDICO")
                .requestMatchers(HttpMethod.POST, "/medico").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.PUT, "/medico").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.DELETE, "/medico").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/paciente/nome").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/paciente/dados").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.POST, "/paciente").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.PUT, "/paciente").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.DELETE, "/paciente").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/consulta/dia").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/consulta/confirmacao").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/consulta/atendimento").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/consulta/paciente").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/consulta/lista").hasRole("MEDICO")
                .requestMatchers(HttpMethod.POST, "/consulta").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.PUT, "/consulta").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.DELETE, "/consulta").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers("/prontuario/**").hasRole("MEDICO")
                .requestMatchers(HttpMethod.GET, "/my/funcionario").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/my/medico").hasRole("MEDICO")
                .anyRequest().hasRole("ADMIN"))
                .csrf((csrf) -> csrf.disable())
                .csrf().disable()
                .csrf().disable()
                .headers().frameOptions().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
