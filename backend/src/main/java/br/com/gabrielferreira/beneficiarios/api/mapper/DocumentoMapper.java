package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.api.dto.DocumentoDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.DocumentoCreateDTO;
import br.com.gabrielferreira.beneficiarios.domain.model.Documento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = FormatMapper.class)
public interface DocumentoMapper {

    @Mapping(target = "tipoDocumento", qualifiedByName = "tipoDocumentoEnum")
    Documento toDocumento(DocumentoCreateDTO documentoCreateDTO);

    default List<Documento> toDocumentos(List<DocumentoCreateDTO> documentoCreateDTOS){
        return documentoCreateDTOS.stream().map(this::toDocumento).toList();
    }

    @Mapping(target = "dataInclusao", qualifiedByName = "formatData")
    @Mapping(target = "dataAtualizacao", qualifiedByName = "formatData")
    DocumentoDTO toDocumentoDto(Documento documento);

    default List<DocumentoDTO> toDocumentosDtos(List<Documento> documentos){
        return documentos.stream().map(this::toDocumentoDto).toList();
    }

    default Page<DocumentoDTO> toDocumentosDtos(Page<Documento> documentos){
        return documentos.map(this::toDocumentoDto);
    }
}
