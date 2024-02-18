package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = {DocumentoMapper.class, FormatMapper.class})
public interface BeneficiarioMapper {

    Beneficiario toBeneficiario(BeneficiarioCreateDTO beneficiarioCreateDTO);

    @Mapping(target = "dataInclusao", qualifiedByName = "formatData")
    @Mapping(target = "dataAtualizacao", qualifiedByName = "formatData")
    BeneficiarioDTO toBeneficiarioDto(Beneficiario beneficiario);

    @Mapping(target = "documentos", ignore = true)
    @Mapping(target = "dataInclusao", qualifiedByName = "formatData")
    @Mapping(target = "dataAtualizacao", qualifiedByName = "formatData")
    BeneficiarioDTO toBeneficiarioSemDocumentosDto(Beneficiario beneficiario);

    default Page<BeneficiarioDTO> toBeneficiariosSemDocumentosDtos(Page<Beneficiario> beneficiarios){
        return beneficiarios.map(this::toBeneficiarioSemDocumentosDto);
    }
}
