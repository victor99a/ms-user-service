package com.victor.ms_user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta enviada luego de un registro o login exitoso. Contiene el token JWT y datos básicos del usuario.")
public class AuthResponse {

    @Schema(
            description = "Token JWT que debe enviarse en el encabezado Authorization: Bearer {token}.",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    )
    private String token;

    @Schema(
            description = "Nombre del usuario autenticado.",
            example = "Víctor Barrera"
    )
    private String nombre;

    @Schema(
            description = "Rol principal del usuario dentro del sistema.",
            example = "ADMINISTRADOR"
    )
    private String rol;
}
