package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta contendo dados do usuário")
public record UserResponse(
    @Schema(description = "ID único do usuário", example = "550e8400-e29b-41d4-a716-446655440000")
    String id,
    
    @Schema(description = "Nome do usuário", example = "João Silva")
    String name,
    
    @Schema(description = "Email do usuário", example = "joao@example.com")
    String email,
    
    @Schema(description = "Senha do usuário (não deve ser exposta)", example = "***")
    String password,
    
    @Schema(description = "Número de telefone do usuário", example = "+55 11 98765-4321")
    String phoneNumber
) {}
