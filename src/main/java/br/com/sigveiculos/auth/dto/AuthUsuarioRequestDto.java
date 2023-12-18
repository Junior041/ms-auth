package br.com.sigveiculos.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUsuarioRequestDto{
    @Schema(example = "login22")
    private String login;

    @Schema(example = "acesso22")
    private String acesso;

}