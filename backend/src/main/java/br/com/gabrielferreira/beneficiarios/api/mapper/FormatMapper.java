package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.domain.model.enums.TipoDocumentoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.ZonedDateTime;

import static br.com.gabrielferreira.beneficiarios.utils.DataUtils.*;

@Mapper(componentModel = "spring")
public interface FormatMapper {

    @Named("formatData")
    default ZonedDateTime formatDate(ZonedDateTime data){
        return toFusoPadraoSistema(data);
    }

    @Named("tipoDocumentoEnum")
    default TipoDocumentoEnum tipoDocumentoEnum(String tipoDocumento){
        return TipoDocumentoEnum.toTipoDocumentoEnum(tipoDocumento);
    }
}
