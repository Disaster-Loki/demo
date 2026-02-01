package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entities.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.ForgotPasswordRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, ForgotPasswordRepository forgotPasswordRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(String id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return mapper.toResponse(user);
    }

    public UserResponse create(UserRequest request) {
        User user = mapper.toEntity(request);
        // ✅ Criptografar senha antes de salvar
        user.setPassword(passwordEncoder.encode(request.password()));
        User saved = repository.save(user);
        return mapper.toResponse(saved);
    }

    public String deleteById(String id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // Delete associated ForgotPassword record first to avoid cascade issues
        forgotPasswordRepository.findByUser(user).ifPresent(forgotPasswordRepository::delete);
        
        repository.deleteById(id);
        return "✅ Usuário " + user.getName() + " deletado com sucesso.";
    }
}

