package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para alterar senha")
public record ChangePassword(
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 50, message = "Senha deve ter entre 8 e 50 caracteres")
    @Schema(description = "Nova senha (mínimo 8 caracteres)", example = "NovaSenha123!")
    String password,
    
    @NotBlank(message = "Confirmação de senha é obrigatória")
    @Size(min = 8, max = 50, message = "Senha deve ter entre 8 e 50 caracteres")
    @Schema(description = "Confirmação da nova senha", example = "NovaSenha123!")
    String repeatPassword
) {}
