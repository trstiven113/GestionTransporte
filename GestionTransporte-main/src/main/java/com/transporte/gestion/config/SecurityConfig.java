package com.transporte.gestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.transporte.gestion.model.Usuario;
import com.transporte.gestion.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .httpBasic(httpBasic -> {})
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/camiones").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/camiones/**").hasAnyRole("ADMIN", "SUPERVISOR")
                .requestMatchers(HttpMethod.PUT, "/api/camiones/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/camiones/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/conductores").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/conductores/**").hasAnyRole("ADMIN", "SUPERVISOR")
                .requestMatchers(HttpMethod.PUT, "/api/conductores/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/conductores/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/asociaciones/**").hasRole("SUPERVISOR")
                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> usuarioRepository.findByUsername(username)
                .map(this::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    private org.springframework.security.core.userdetails.UserDetails toUserDetails(Usuario usuario) {
        String rol = usuario.getRol().toUpperCase().replace("ROLE_", "");

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(rol)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
