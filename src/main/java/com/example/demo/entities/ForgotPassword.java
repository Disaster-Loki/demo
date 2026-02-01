package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Schema(description = "Entidade para armazenar requests de recuperação de senha com OTP")
public class ForgotPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do request de recuperação", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Código OTP gerado (6 dígitos)", example = "123456")
    private Integer otp;
    
    @Column(nullable = false)
    @Schema(description = "Data e hora de expiração do OTP", example = "2026-02-01T14:20:00")
    private Date expirationTime;

    @OneToOne(cascade = CascadeType.REMOVE)
    @Schema(description = "Usuário associado ao request de recuperação")
    private User user;
}
