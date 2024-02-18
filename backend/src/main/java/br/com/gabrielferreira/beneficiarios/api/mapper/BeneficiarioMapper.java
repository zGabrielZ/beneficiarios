package br.com.gabrielferreira.beneficiarios.api.mapper;

import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioResumidoDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.update.BeneficiarioUpdateDTO;
import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = {DocumentoMapper.class, FormatMapper.class})
public interface BeneficiarioMapper {

    Beneficiario toBeneficiario(BeneficiarioCreateDTO beneficiarioCreateDTO);

    Beneficiario toBeneficiario(BeneficiarioUpdateDTO beneficiarioUpdateDTO);

    @Mapping(target = "dataInclusao", qualifiedByName = "formatData")
    @Mapping(target = "dataAtualizacao", qualifiedByName = "formatData")
    BeneficiarioDTO toBeneficiarioDto(Beneficiario beneficiario);

    @Mapping(target = "dataInclusao", qualifiedByName = "formatData")
    @Mapping(target = "dataAtualizacao", qualifiedByName = "formatData")
    BeneficiarioResumidoDTO toBeneficiarioResumidoDto(Beneficiario beneficiario);

    default Page<BeneficiarioResumidoDTO> toBeneficiariosResumidoDtos(Page<Beneficiario> beneficiarios){
        return beneficiarios.map(this::toBeneficiarioResumidoDto);
    }
}
