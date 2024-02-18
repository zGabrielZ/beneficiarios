package br.com.gabrielferreira.beneficiarios.domain.model.enums;

import br.com.gabrielferreira.beneficiarios.domain.exception.MsgException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDocumentoEnum {

    CARTAO_IDENTIFICACAO_PLANO_SAUDE("Cartão de identificação do plano de saúde"),
    FORMULATORIO_ADMISSAO("Formulário de admissão"),
    TERMO_CONSENTIMENTO_TRATAMENTO_MEDICO("Termo de consentimento para tratamento médico"),
    EOB("Explicação dos Benefícios"),
    FATURAS_RECIBOS("Faturas e recibos"),
    INSTRUCAO_ALTA("Instruções de alta");

    private final String descricao;
    
    public static TipoDocumentoEnum toTipoDocumentoEnum(String tipoDocumento){
        for (TipoDocumentoEnum valor : TipoDocumentoEnum.values()) {
            if(valor.name().equals(tipoDocumento)){
                return valor;
            }
        }
        throw new MsgException("Tipo de documento informado inválido");
    }
}
