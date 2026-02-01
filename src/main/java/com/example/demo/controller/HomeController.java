package com.example.demo.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@Tag(name = "🏠 Home", description = "Endpoint para servir a página inicial")
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @Operation(summary = "Página inicial", description = "Retorna a página inicial do website com interface para testar a API")
    @ApiResponse(responseCode = "200", description = "Página HTML retornada com sucesso")
    public ResponseEntity<String> index() throws IOException {
        Resource resource = new ClassPathResource("templates/index.html");
        String content = Files.readString(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(content);
    }
}
