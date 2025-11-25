package com.victor.ms_user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos necesarios para registrar un nuevo usuario en el sistema.")
public class RegistroRequest {

    @Schema(
            description = "Nombre del usuario.",
            example = "Víctor"
    )
    private String nombre;

    @Schema(
            description = "Apellido del usuario.",
            example = "Barrera"
    )
    private String apellido;

    @Schema(
            description = "Email único del usuario. Se utilizará para iniciar sesión.",
            example = "victor@example.com"
    )
    private String email;

    @Schema(
            description = "Contraseña en texto plano (será cifrada en el backend).",
            example = "MiClaveSegura123"
    )
    private String password;

    @Schema(
            description = "Rol asignado al usuario. Por ejemplo, Administrador o Trabajador.",
            example = "ADMIN"
    )
    private String rol; // Administrador o trabajador
}
