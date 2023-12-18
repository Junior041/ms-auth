package br.com.sigveiculos.auth.repositories;

import br.com.sigveiculos.auth.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findUsuarioByLogin(String Login);

    Optional<UsuarioEntity> findUsuarioByFkPessoa(Integer fkPessoa);
}
