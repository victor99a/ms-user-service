package com.victor.ms_user_service.repository;

import com.victor.ms_user_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByNombre(String email);
}
