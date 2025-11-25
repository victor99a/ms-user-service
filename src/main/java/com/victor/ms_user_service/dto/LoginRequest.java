package com.victor.ms_user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Credenciales utilizadas para iniciar sesión en el sistema.")
public class LoginRequest {

    @Schema(
            description = "Email registrado del usuario.",
            example = "victor@example.com"
    )
    private String email;

    @Schema(
            description = "Contraseña del usuario.",
            example = "MiClaveSegura123"
    )
    private String password;
}
