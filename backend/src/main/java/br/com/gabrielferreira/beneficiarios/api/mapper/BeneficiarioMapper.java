package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DocumentoMapper.class, FormatMapper.class})
public interface BeneficiarioMapper {

    Beneficiario toBeneficiario(BeneficiarioCreateDTO beneficiarioCreateDTO);

    @Mapping(target = "dataInclusao", qualifiedByName = "formatData")
    @Mapping(target = "dataAtualizacao", qualifiedByName = "formatData")
    BeneficiarioDTO toBeneficiarioDto(Beneficiario beneficiario);
}
