package br.com.sigveiculos.auth.core;

import br.com.sigveiculos.auth.dto.CreateUsuarioDto;
import br.com.sigveiculos.auth.entities.UsuarioEntity;
import br.com.sigveiculos.auth.exceptions.UsuarioJaCadastrado;
import br.com.sigveiculos.auth.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CreateUsuarioUseCase {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public UsuarioEntity execute(CreateUsuarioDto createUsuarioDTO){
        this.usuarioRepository.findUsuarioByFkPessoa(createUsuarioDTO.getFkPessoa()).ifPresent((UsuarioEntity) -> {
            throw new UsuarioJaCadastrado();
        });
        this.usuarioRepository.findUsuarioByLogin(createUsuarioDTO.getLogin()).ifPresent((UsuarioEntity) -> {
            throw new UsuarioJaCadastrado();
        });
        UsuarioEntity usuario = UsuarioEntity.builder()
                .login(createUsuarioDTO.getLogin())
                .dfInativo(createUsuarioDTO.getDfInativo())
                .fkUserInsert(createUsuarioDTO.getFkPessoa())
                .dataInsert(LocalDateTime.now())
                .build();
        var password = this.passwordEncoder.encode(createUsuarioDTO.getAcesso());
        usuario.setAcesso(password);
        return this.usuarioRepository.save(usuario);
    }
}
