package br.com.gabrielferreira.beneficiarios.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Tipo do documento", example = "CARTAO_IDENTIFICACAO_PLANO_SAUDE")
    private String tipo;

    @Schema(description = "Descrição do tipo do documento", example = "Cartão de identificação do plano de saúde")
    private String descricao;
}
