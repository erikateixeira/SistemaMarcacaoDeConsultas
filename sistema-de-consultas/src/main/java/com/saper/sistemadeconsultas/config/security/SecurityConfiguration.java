package com.saper.sistemadeconsultas.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests( (authz)->authz
                .requestMatchers(HttpMethod.GET, "/funcionario/nome").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/funcionario/dados").hasAnyRole("ADMIN", "MEDICO")
                .requestMatchers(HttpMethod.GET, "/funcionario/username").hasAnyRole("ADMIN","RECEPCIONISTA")
                .requestMatchers(HttpMethod.POST, "/funcionario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/funcionario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/funcionario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/medico/nome").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/medico/dados").hasAnyRole("ADMIN", "RECEPCIONISTA", "MEDICO")
                .requestMatchers(HttpMethod.GET, "/medico/username").hasRole("MEDICO")
                .requestMatchers(HttpMethod.POST, "/medico").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.PUT, "/medico").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.DELETE, "/medico").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/paciente/nome").hasAnyRole("ADMIN", "RECEPCIONISTA")
                .requestMatchers(HttpMethod.GET, "/paciente/dados").hasAnyRole("ADMIN", "RECEPCIONISTA","MEDICO")
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
                .requestMatchers(HttpMethod.GET,"/my/usuario").hasAnyRole("ADMIN", "RECEPCIONISTA","MEDICO")
                .anyRequest().hasRole("ADMIN"));
        http.csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }




}
