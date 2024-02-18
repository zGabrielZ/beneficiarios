package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.api.dto.TipoDocumentoDTO;
import br.com.gabrielferreira.beneficiarios.domain.model.enums.TipoDocumentoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoDocumentoMapper {

    @Mapping(target = "tipo", expression = "java(tipoDocumentoEnum.name())")
    TipoDocumentoDTO toTipoDocumentoDto(TipoDocumentoEnum tipoDocumentoEnum);

    default List<TipoDocumentoDTO> toTipoDocumentosDtos(List<TipoDocumentoEnum> tipos){
        return tipos.stream().map(this::toTipoDocumentoDto).toList();
    }
}
