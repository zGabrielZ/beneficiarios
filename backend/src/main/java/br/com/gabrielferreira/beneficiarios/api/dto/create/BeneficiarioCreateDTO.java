package br.com.gabrielferreira.beneficiarios.api.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiarioCreateDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "Nome do beneficiário", example = "Gabriel Ferreira")
    @NotBlank
    @Size(max = 150)
    private String nome;

    @Schema(description = "Telefone do beneficiário", example = "(99) 99999-9999")
    @NotBlank
    private String telefone;

    @Schema(description = "Data de nascimento do beneficiário", example = "1985-05-23")
    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    @Schema(description = "Lista de documentos do beneficiário")
    @Valid
    @NotEmpty
    private List<DocumentoCreateDTO> documentos = new ArrayList<>();
}
