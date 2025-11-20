package com.victor.ms_user_service.dto;


import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String nombre;
    private String rol;
}
