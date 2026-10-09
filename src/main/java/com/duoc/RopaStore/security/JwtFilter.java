package com.duoc.RopaStore.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@Order(1)
public class JwtFilter implements Filter {

    private final JwtService jwtService;

    // Rutas que nunca requieren token
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/api/auth/login",
            "/actuator/health"
    );

    // Métodos que SIEMPRE requieren token
    private static final Set<String> PROTECTED_METHODS = Set.of(
            "POST", "PUT", "DELETE", "PATCH"
    );

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();
        String method = request.getMethod();

        // 1. Rutas públicas -> dejar pasar
        if (PUBLIC_PATHS.stream().anyMatch(path::startsWith)) {
            chain.doFilter(req, res);
            return;
        }

        // 2. Métodos no protegidos (GET, OPTIONS, HEAD) -> dejar pasar
        if (!PROTECTED_METHODS.contains(method)) {
            chain.doFilter(req, res);
            return;
        }

        // 3. Métodos protegidos -> exigir token válido
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            responderError(response, 401, "Token no proporcionado");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtService.esValido(token)) {
            responderError(response, 401, "Token inválido o expirado");
            return;
        }

        // Token OK -> dejar pasar
        chain.doFilter(req, res);
    }

    private void responderError(HttpServletResponse response, int status, String mensaje)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"" + mensaje + "\"}");
    }
}