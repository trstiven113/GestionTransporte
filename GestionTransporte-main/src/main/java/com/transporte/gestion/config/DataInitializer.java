package com.transporte.gestion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.transporte.gestion.model.Usuario;
import com.transporte.gestion.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRol("ADMIN");
                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByUsername("supervisor").isEmpty()) {
                Usuario supervisor = new Usuario();
                supervisor.setUsername("supervisor");
                supervisor.setPassword(passwordEncoder.encode("super123"));
                supervisor.setRol("SUPERVISOR");
                usuarioRepository.save(supervisor);
            }
        };
    }
}
