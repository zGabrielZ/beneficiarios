package br.com.gabrielferreira.beneficiarios.tests;

import br.com.gabrielferreira.beneficiarios.api.dto.create.DocumentoCreateDTO;

import java.util.Arrays;
import java.util.List;

public class DocumentoFactory {

    private DocumentoFactory(){}

    public static List<DocumentoCreateDTO> criarDocumentos(){
        DocumentoCreateDTO documento1 = DocumentoCreateDTO.builder()
                .tipoDocumento("FATURAS_RECIBOS")
                .descricao("A fatura desse beneficiário já está disponível para o pagamento")
                .build();

        DocumentoCreateDTO documento2 = DocumentoCreateDTO.builder()
                .tipoDocumento("INSTRUCAO_ALTA")
                .descricao("O beneficiário já foi operado pelo hospital de SP")
                .build();

        return Arrays.asList(documento1, documento2);
    }
}
