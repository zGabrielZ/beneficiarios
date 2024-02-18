package br.com.gabrielferreira.beneficiarios.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Id do documento", example = "1")
    private Long id;

    @Schema(description = "Tipo de documento do beneficiário", example = "FATURAS_RECIBOS")
    private String tipoDocumento;

    @Schema(description = "Descrição do documento do beneficiário", example = "A fatura desse beneficiário já está disponível para o pagamento")
    private String descricao;

    @Schema(description = "Data inclusão do documento do beneficiário", example = "2024-02-18T12:30:23.177681-03:00")
    private ZonedDateTime dataInclusao;

    @Schema(description = "Data atualização do documento do beneficiário", example = "2024-02-18T12:30:23.177681-03:00")
    private ZonedDateTime dataAtualizacao;
}
