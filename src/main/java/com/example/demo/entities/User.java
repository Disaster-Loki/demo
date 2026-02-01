package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "users")
@Schema(description = "Entidade representando um usuário do sistema")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "ID único do usuário", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Column(name = "name", length = 100, nullable = false)
    @Schema(description = "Nome completo do usuário", example = "João Silva Santos")
    private String name;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Schema(description = "Email único do usuário", example = "joao@example.com")
    private String email;

    @Column(name = "password", length = 200, nullable = false)
    @Schema(description = "Senha criptografada do usuário")
    private String password;

    @Column(name = "phone_number", length = 20, nullable = false, unique = true)
    @Schema(description = "Número de telefone do usuário", example = "+55 11 98765-4321")
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

    // Construtor vazio (obrigatório para JPA)
    public User() {
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
