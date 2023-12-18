package br.com.sigveiculos.auth.core;

import br.com.sigveiculos.auth.dto.AuthUsuarioRequestDto;
import br.com.sigveiculos.auth.dto.AuthUsuarioResponseDto;
import br.com.sigveiculos.auth.entities.UsuarioEntity;
import br.com.sigveiculos.auth.repositories.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
@Service
public class AuthUsuarioUseCase {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUsuarioResponseDto execute(AuthUsuarioRequestDto authUsuarioRequestDto) throws AuthenticationException {
        UsuarioEntity usuario = this.usuarioRepository.findUsuarioByLogin(authUsuarioRequestDto.getLogin())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorretos.");
                });
        Boolean passwordMatches = this.passwordEncoder.matches(authUsuarioRequestDto.getAcesso(), usuario.getAcesso());

        if (!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256("KeyProvisoria");
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create().withIssuer("SIG")
                .withSubject(usuario.getIdUsuario().toString())
                .withClaim("roles", Arrays.asList("usuario"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
        var authAuthenticateResponse = AuthUsuarioResponseDto.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authAuthenticateResponse;
    }
}
