package br.com.gabrielferreira.beneficiarios.api.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class DocumentoCreateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Tipo de documento do beneficiário", example = "FATURAS_RECIBOS")
    @NotNull
    private String tipoDocumento;

    @Schema(description = "Descrição do documento do beneficiário", example = "A fatura desse beneficiário já está disponível para o pagamento")
    @NotBlank
    @Size(max = 300)
    private String descricao;
}
