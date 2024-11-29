package com.appstra.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
                .csrf().disable() // permite seguridad pero al colocar JWT precenta inconvenientes en la aplicacion
                .cors().and() // permite la comunicacion de 2 origenes diferentes EJEMPLO: localHost:8080 y front 42000
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //se quitan las sessiones
                .authorizeHttpRequests()// para autorizar las peticiones HTTP
                .requestMatchers("api/v1/auntenticar/**").permitAll() // permite consumir sin autenticacion
                .anyRequest() // cualquier peticion que llegue
                .authenticated()// debe estar auntenticado
                .and() // y
                // se quita para colocar el filtro de JWT .httpBasic(); // debe estar en la metologia Basic
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
