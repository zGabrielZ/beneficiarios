package br.com.gabrielferreira.beneficiarios.domain.service;

import br.com.gabrielferreira.beneficiarios.domain.model.Documento;
import br.com.gabrielferreira.beneficiarios.domain.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final BeneficiarioService beneficiarioService;

    private final DocumentoRepository documentoRepository;

    public Page<Documento> buscarDocumentosPorBeneficiarioPaginados(Long idBeneficiario, Integer page, Integer size){
        beneficiarioService.buscarBeneficiarioPorId(idBeneficiario);
        PageRequest pageRequest = PageRequest.of(page, size);
        return documentoRepository.buscarDocumentosPorBeneficiario(idBeneficiario, pageRequest);
    }
}
