package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeneficiarioMapper {

    Beneficiario toBeneficiario(BeneficiarioCreateDTO beneficiarioCreateDTO);

    BeneficiarioDTO toBeneficiarioDto(Beneficiario beneficiario);
}
