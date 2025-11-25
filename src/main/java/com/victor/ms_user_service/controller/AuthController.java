package com.victor.ms_user_service.controller;

import com.victor.ms_user_service.dto.AuthResponse;
import com.victor.ms_user_service.dto.LoginRequest;
import com.victor.ms_user_service.dto.RegistroRequest;
import com.victor.ms_user_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
        name = "Autenticación",
        description = "Endpoints para registro y login de usuarios, generación de JWT y asignación de roles."
)
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = """
                    Registra un nuevo usuario en el sistema y le asigna un rol inicial.
                    Devuelve un token JWT y datos básicos del usuario para futuras peticiones autenticadas.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario registrado correctamente.",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos en la solicitud (por ejemplo campos vacíos o email mal formado)."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El email ya está registrado en el sistema."
            )
    })
    public ResponseEntity<AuthResponse> registrar(
            @RequestBody(
                    description = "Datos del usuario a registrar.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RegistroRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de registro",
                                    value = """
                                            {
                                              "nombre": "Víctor",
                                              "apellido": "Barrera",
                                              "email": "victor@example.com",
                                              "password": "MiClaveSegura123",
                                              "rol": "USER"
                                            }
                                            """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid RegistroRequest request
    ) {
        return ResponseEntity.ok(authService.registrar(request));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Iniciar sesión",
            description = """
                    Autentica a un usuario utilizando email y contraseña.
                    Si las credenciales son correctas, devuelve un token JWT para acceder a recursos protegidos.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login exitoso, se devuelve el token JWT.",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos en la solicitud."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciales incorrectas (email o contraseña inválidos)."
            )
    })
    public ResponseEntity<AuthResponse> login(
            @RequestBody(
                    description = "Credenciales del usuario.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = LoginRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de login",
                                    value = """
                                            {
                                              "email": "victor@example.com",
                                              "password": "MiClaveSegura123"
                                            }
                                            """
                            )
                    )
            )
            @org.springframework.web.bind.annotation.RequestBody @Valid LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
