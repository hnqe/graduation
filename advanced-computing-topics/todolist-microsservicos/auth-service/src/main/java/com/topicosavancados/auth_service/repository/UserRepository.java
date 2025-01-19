package com.topicosavancados.auth_service.repository;

import com.topicosavancados.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username); // Atualizado para retornar Optional
    boolean existsByUsername(String username);     // Para verificar se o usuário já existe
}

