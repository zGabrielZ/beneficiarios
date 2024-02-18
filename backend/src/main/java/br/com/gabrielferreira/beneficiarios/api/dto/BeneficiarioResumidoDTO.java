package br.com.gabrielferreira.beneficiarios.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiarioResumidoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Id do beneficiário", example = "1")
    private Long id;

    @Schema(description = "Nome do beneficiário", example = "Gabriel Ferreira")
    private String nome;

    @Schema(description = "Telefone do beneficiário", example = "(99) 99999-9999")
    private String telefone;

    @Schema(description = "Data de nascimento do beneficiário", example = "1985-05-23")
    private LocalDate dataNascimento;

    @Schema(description = "Data inclusão do beneficiário", example = "2024-02-18T12:30:23.177681-03:00")
    private ZonedDateTime dataInclusao;

    @Schema(description = "Data atualização do beneficiário", example = "2024-02-18T13:30:23.177681-03:00")
    private ZonedDateTime dataAtualizacao;
}
