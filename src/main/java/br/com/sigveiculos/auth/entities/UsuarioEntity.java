package br.com.sigveiculos.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUsuario;

    private Integer fkPessoa;
    private LocalDateTime dfInativo;
    private String acesso;
    private String login;

    private Integer fkUserInsert;
    private Integer fkUserUpdate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataInsert", nullable = false)
    private LocalDateTime dataInsert;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataUpdate", insertable = false)
    private LocalDateTime dataUpdate;
}
