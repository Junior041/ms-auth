package br.com.sigveiculos.auth.controller;

import br.com.sigveiculos.auth.core.AuthUsuarioUseCase;
import br.com.sigveiculos.auth.core.CreateUsuarioUseCase;
import br.com.sigveiculos.auth.dto.AuthUsuarioRequestDto;
import br.com.sigveiculos.auth.dto.CreateUsuarioDto;
import br.com.sigveiculos.auth.entities.UsuarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario")
public class UsuarioController {
    @Autowired
    private CreateUsuarioUseCase createUsuarioUseCase;

    @Autowired
    private AuthUsuarioUseCase authUsuarioUseCase;
    @Operation(
            summary = "Cadastro de usuário",
            description = "Essa função é responsável por cadastrar um usuário"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = UsuarioEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já cadastrado.")
    })
    @PostMapping("/criar")
    public ResponseEntity<Object> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDTO){
        try {
            UsuarioEntity response = this.createUsuarioUseCase.execute(createUsuarioDTO);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Operation(
            summary = "Login de usuário",
            description = "Essa função é responsável por realizar o login"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = UsuarioEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Username/password incorretos.")
    })
    @PostMapping("/logar")
    public ResponseEntity<Object> logar(@RequestBody AuthUsuarioRequestDto authUsuarioRequestDto){
        try {
            var response = this.authUsuarioUseCase.execute(authUsuarioRequestDto);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
