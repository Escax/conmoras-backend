package com.conmoras.conmoras_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Login de usuarios")
public class AuthController {

    // Usuario hardcodeado — para producción usarías BD + JWT
    private static final String USUARIO = "admin";
    private static final String PASSWORD = "conmoras123";

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String usuario = credenciales.get("usuario");
        String password = credenciales.get("password");

        if (USUARIO.equals(usuario) && PASSWORD.equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Login exitoso",
                    "usuario", usuario,
                    "rol", "ADMIN"
            ));
        }

        return ResponseEntity.status(401)
                .body(Map.of("mensaje", "Credenciales incorrectas"));
    }
}

