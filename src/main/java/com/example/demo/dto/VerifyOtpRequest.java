package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para verificar OTP")
public record VerifyOtpRequest(
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Schema(description = "Email do usuário", example = "joao@example.com")
    String email,
    
    @NotNull(message = "OTP é obrigatório")
    @Schema(description = "Código OTP recebido por email (6 dígitos)", example = "123456")
    Integer otp
) {}
