package com.victor.ms_user_service.service;

import com.victor.ms_user_service.dto.AuthResponse;
import com.victor.ms_user_service.dto.LoginRequest;
import com.victor.ms_user_service.dto.RegistroRequest;
import com.victor.ms_user_service.model.Rol;
import com.victor.ms_user_service.model.Usuario;
import com.victor.ms_user_service.repository.UsuarioRepository;
import com.victor.ms_user_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthResponse registrar(RegistroRequest request) {

        // Opcional: validar que no exista el email
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.valueOf(request.getRol())) // "ADMIN", "CLIENTE", etc.
                .build();

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().name());


        return new AuthResponse(token, usuario.getNombre(), usuario.getRol().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().name());

        return new AuthResponse(token, usuario.getNombre(), usuario.getRol().name());
    }
}