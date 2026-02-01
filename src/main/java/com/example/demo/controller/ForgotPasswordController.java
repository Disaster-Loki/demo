package com.example.demo.controller;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ChangePassword;
import com.example.demo.dto.MailBody;
import com.example.demo.dto.VerifyOtpRequest;
import com.example.demo.entities.ForgotPassword;
import com.example.demo.entities.User;
import com.example.demo.repository.ForgotPasswordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/forgot-password")
@Tag(name = "🔐 Recuperação de Senha", description = "Endpoints para recuperação de senha via OTP")
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/send/{email}")
    @Operation(summary = "Enviar OTP", description = "Gera e envia um código OTP para o email do usuário (válido por 5 minutos)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OTP enviado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<String> sendOtp(@PathVariable String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Integer otp = generateOtp();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + (5 * 60 * 1000)))
                .user(user)
                .build();

        forgotPasswordRepository.save(forgotPassword);

        MailBody mailBody = MailBody.builder()
                .to(email)
                .subject("🔐 Password Reset Verification")
                .text("Seu código OTP é: " + otp + "\n\nVálido por 5 minutos.\n\nNão compartilhe este código com ninguém!")
                .build();

        emailService.sendSimpleMessage(mailBody);

        return ResponseEntity.ok("✅ OTP enviado para o email com sucesso.");
    }

    @PostMapping("/verify-otp")
    @Operation(summary = "Verificar OTP", description = "Verifica se o OTP fornecido é válido e não expirou")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OTP verificado com sucesso"),
        @ApiResponse(responseCode = "400", description = "OTP inválido ou expirado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ForgotPassword forgotPassword = forgotPasswordRepository
                .findByOtpAndUser(request.otp(), user)
                .orElseThrow(() -> new RuntimeException("OTP inválido"));

        if (forgotPassword.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.delete(forgotPassword);
            return new ResponseEntity<>("❌ OTP expirado. Solicite um novo.", HttpStatus.BAD_REQUEST);
        }

        forgotPasswordRepository.save(forgotPassword);

        return ResponseEntity.ok("✅ OTP verificado com sucesso. Você pode agora alterar sua senha.");
    }

    @PostMapping("/change-password/{email}")
    @Operation(summary = "Alterar Senha", description = "Altera a senha do usuário após verificação do OTP")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou senhas não conferem"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody ChangePassword changePassword,
            @PathVariable String email) {

        if (!changePassword.password().equals(changePassword.repeatPassword())) {
            return new ResponseEntity<>("❌ As senhas não conferem!", HttpStatus.BAD_REQUEST);
        }

        userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        String encryptedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassword(email, encryptedPassword);

        return ResponseEntity.ok("✅ Senha alterada com sucesso. Você pode fazer login com a nova senha.");
    }

    private Integer generateOtp() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(100_000, 999_999);
    }
}
