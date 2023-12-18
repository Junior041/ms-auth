package br.com.sigveiculos.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsuarioDto {
    @Schema(example = "1")
    private Integer fkPessoa;

    @Schema(example = "2023-12-18T15:30:00")
    private LocalDateTime dfInativo;

    @Schema(example = "acesso22")
    private String acesso;

    @Schema(example = "login22")
    private String login;
}
