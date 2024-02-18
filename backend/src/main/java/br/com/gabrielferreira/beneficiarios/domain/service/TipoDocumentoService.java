package br.com.gabrielferreira.beneficiarios.domain.service;

import br.com.gabrielferreira.beneficiarios.domain.model.enums.TipoDocumentoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoDocumentoService {

    public List<TipoDocumentoEnum> buscarTipoDocumentos(){
        List<TipoDocumentoEnum> tiposDocumentos = Arrays.asList(TipoDocumentoEnum.values());
        tiposDocumentos.sort(Comparator.comparing(TipoDocumentoEnum::getDescricao));
        return tiposDocumentos;
    }
}
