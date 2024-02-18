package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.domain.exception.model.ErroPadraoCampos;
import br.com.gabrielferreira.beneficiarios.domain.exception.model.ErroPadraoFormulario;
import br.com.gabrielferreira.beneficiarios.domain.exception.model.ErroPadrao;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ErroPadraoMapper {

    public ErroPadrao toErroPadrao(ZonedDateTime dataAtual, Integer status, String titulo, String mensagem, String caminhoUrl){
        return ErroPadrao.builder()
                .dataAtual(dataAtual)
                .status(status)
                .titulo(titulo)
                .status(status)
                .mensagem(mensagem)
                .caminhoUrl(caminhoUrl)
                .build();
    }

    public ErroPadraoCampos toErroPadraoCampos(ZonedDateTime dataAtual, Integer status, String titulo, String mensagem, String caminhoUrl){
        ErroPadraoCampos erroPadraoCampos = new ErroPadraoCampos();
        erroPadraoCampos.setDataAtual(dataAtual);
        erroPadraoCampos.setStatus(status);
        erroPadraoCampos.setTitulo(titulo);
        erroPadraoCampos.setMensagem(mensagem);
        erroPadraoCampos.setCaminhoUrl(caminhoUrl);
        return erroPadraoCampos;
    }

    public ErroPadraoFormulario toErroPadraoFormulario(String campo, String mensagem){
        return ErroPadraoFormulario.builder()
                .campo(campo)
                .mensagem(mensagem)
                .build();
    }
}
