package com.duoc.RopaStore.controller;

import com.duoc.RopaStore.dto.LoginRequest;
import com.duoc.RopaStore.dto.LoginResponse;
import com.duoc.RopaStore.model.Admin;
import com.duoc.RopaStore.repository.AdminRepository;
import com.duoc.RopaStore.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(AdminRepository adminRepository, JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(request.email());

        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        Admin admin = adminOpt.get();
        if (!passwordEncoder.matches(request.password(), admin.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String token = jwtService.generarToken(admin.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, admin.getEmail()));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Token no proporcionado");
        }

        String token = authHeader.substring(7);
        if (!jwtService.esValido(token)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }

        String email = jwtService.extraerEmail(token);
        return ResponseEntity.ok("Autenticado como: " + email);
    }
}