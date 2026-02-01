package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Configuração de criptografia de senhas usando BCrypt
 */
@Configuration
@Tag(name = "🔐 Configuração", description = "Configurações de segurança da aplicação")
public class PasswordEncoderConfig {

    /**
     * Bean para codificar e validar senhas com BCrypt
     * 
     * BCrypt é um algoritmo adaptativo de hash de senha que:
     * - É resistente a ataques de força bruta
     * - Inclui um "salt" automático para cada senha
     * - Pode aumentar a complexidade com o tempo (rounds)
     * 
     * @return PasswordEncoder configurado com BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
