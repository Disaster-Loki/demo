package com.example.demo.dto;

import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Email a ser enviado")
@Builder
public record MailBody(
    @Schema(description = "Endereço de email destinatário", example = "joao@example.com")
    String to, 
    
    @Schema(description = "Assunto do email", example = "🔐 Password Reset Verification")
    String subject, 
    
    @Schema(description = "Conteúdo do email", example = "Seu código OTP é: 123456")
    String text) {}
