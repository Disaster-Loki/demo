package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resposta de erro da API")
public class ErrorResponse {
    @Schema(description = "Timestamp quando o erro ocorreu", example = "2026-02-01T14:20:00")
    private LocalDateTime timestamp;
    
    @Schema(description = "Código HTTP de status do erro", example = "400")
    private int status;
    
    @Schema(description = "Mensagem de erro descritiva", example = "Email já existe no sistema")
    private String message;
    
    @Schema(description = "Caminho da requisição que causou o erro", example = "/api/v1/users")
    private String path;
}
