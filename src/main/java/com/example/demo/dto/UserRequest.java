package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para criar um novo usuário")
public record UserRequest(
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Schema(description = "Nome do usuário", example = "João Silva")
    String name,
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Schema(description = "Email do usuário", example = "joao@example.com")
    String email,
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 50, message = "Senha deve ter entre 8 e 50 caracteres")
    @Schema(description = "Senha (mínimo 8 caracteres)", example = "SenhaForte123!")
    String password,
    
    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    @Schema(description = "Número de telefone (opcional)", example = "+55 11 98765-4321")
    String phoneNumber
) {}