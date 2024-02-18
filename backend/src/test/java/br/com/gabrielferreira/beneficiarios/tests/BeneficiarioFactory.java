package br.com.gabrielferreira.beneficiarios.tests;

import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import java.time.LocalDate;

import static br.com.gabrielferreira.beneficiarios.tests.DocumentoFactory.*;

public class BeneficiarioFactory {

    private BeneficiarioFactory(){}

    public static BeneficiarioCreateDTO criarBeneficiario(){
        return BeneficiarioCreateDTO.builder()
                .nome("João da Silva")
                .telefone("(11) 90909-0909")
                .dataNascimento(LocalDate.of(1985, 10, 25))
                .documentos(criarDocumentos())
                .build();
    }
}
