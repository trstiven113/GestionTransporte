package com.transporte.gestion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.transporte.gestion.model.Usuario;
import com.transporte.gestion.repository.UsuarioRepository;

@Configuration
public class UserDetailsConfig {

    @Bean
    public UserDetailsService userDetailsService(
            UsuarioRepository usuarioRepository) {

        return username -> usuarioRepository.findByUsername(username)
                .map(this::toUserDetails)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));
    }

    private org.springframework.security.core.userdetails.UserDetails toUserDetails(
            Usuario usuario) {

        String rol = usuario.getRol()
                .toUpperCase()
                .replace("ROLE_", "");

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(rol)
                .build();
    }
}