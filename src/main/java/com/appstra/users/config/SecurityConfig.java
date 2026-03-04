package com.appstra.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(csrf -> csrf.disable()) // permite seguridad pero al colocar JWT presenta inconvenientes en la aplicacion
                .cors(Customizer.withDefaults()) // permite la comunicacion de 2 origenes diferentes EJEMPLO: localhost:8080 y front 4200
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ) // se quitan las sesiones
                .authorizeHttpRequests(auth -> auth // para autorizar las peticiones HTTP
                        .requestMatchers("/api/v1/auntenticar/**").permitAll() // permite consumir sin autenticacion
                        .anyRequest() // cualquier peticion que llegue
                        .authenticated() // debe estar autenticado
                )
                // se quita para colocar el filtro de JWT .httpBasic(); // debe estar en la metodologia Basic
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    /**
     * Configuracion para JWT, este puede ir en la capa de servisios
     * @param configuration
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // metodo para encriptar la contraseña
        return new BCryptPasswordEncoder();
    }
}
