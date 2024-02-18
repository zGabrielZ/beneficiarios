package br.com.gabrielferreira.beneficiarios.domain.service;

import br.com.gabrielferreira.beneficiarios.domain.exception.MsgException;
import br.com.gabrielferreira.beneficiarios.domain.exception.NaoEncontradoException;
import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import br.com.gabrielferreira.beneficiarios.domain.model.Documento;
import br.com.gabrielferreira.beneficiarios.domain.model.enums.TipoDocumentoEnum;
import br.com.gabrielferreira.beneficiarios.domain.repository.BeneficiarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static br.com.gabrielferreira.beneficiarios.utils.FormatUtils.*;

@Service
@RequiredArgsConstructor
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;

    @Transactional
    public Beneficiario cadastrar(Beneficiario beneficiario){
        validarCamposBeneficiario(beneficiario);
        validarCamposDocumentos(beneficiario.getDocumentos());

        validarTipoTelefone(beneficiario.getTelefone());
        validarDocumentos(beneficiario);

        beneficiario = beneficiarioRepository.save(beneficiario);
        return beneficiario;
    }

    public Page<Beneficiario> buscarBeneficiariosPaginados(Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return beneficiarioRepository.buscarBeneficiarios(pageRequest);
    }

    public Beneficiario buscarBeneficiarioPorId(Long id){
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Beneficiário não encontrado"));
    }

    @Transactional
    public void deletarBeneficiarioPorId(Long id){
        Beneficiario beneficiario = buscarBeneficiarioPorId(id);
        beneficiarioRepository.delete(beneficiario);
    }

    @Transactional
    public Beneficiario atualizarBeneficiario(Long id, Beneficiario beneficiario){
        Beneficiario beneficiarioEncontrado = buscarBeneficiarioPorId(id);

        validarCamposBeneficiario(beneficiario);
        validarTipoTelefone(beneficiario.getTelefone());

        preencherCamposBeneficiario(beneficiarioEncontrado, beneficiario);

        beneficiarioEncontrado = beneficiarioRepository.save(beneficiarioEncontrado);
        return beneficiarioEncontrado;
    }

    private void validarCamposBeneficiario(Beneficiario beneficiario){
        beneficiario.setNome(beneficiario.getNome().trim());
        beneficiario.setTelefone(beneficiario.getTelefone().trim());
    }

    private void validarCamposDocumentos(List<Documento> documentos){
        documentos.forEach(documento -> documento.setDescricao(documento.getDescricao().trim()));
    }

    private void validarTipoTelefone(String telefone){
        String telefoneResidencialPattern = "\\(\\d{2}\\)\\s\\d{4}-\\d{4}";
        String celularPattern = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}";

        boolean isTelefoneResidencialValid = validarFormato(telefoneResidencialPattern, telefone);
        boolean isCelularValid = validarFormato(celularPattern, telefone);

        if(!isTelefoneResidencialValid && !isCelularValid){
            throw new MsgException("O tipo de telefone está incorreto. Exemplo: (99) 9999-9999 ou (99) 99999-9999");
        }
    }

    private void validarDocumentos(Beneficiario beneficiario){
        validarTipoDocumentoRepetido(beneficiario.getDocumentos());
        for (Documento documento : beneficiario.getDocumentos()) {
            documento.setBeneficiario(beneficiario);
        }
    }

    private void validarTipoDocumentoRepetido(List<Documento> documentos){
        List<TipoDocumentoEnum> tipoDocumentos = documentos.stream().map(Documento::getTipoDocumento).toList();
        tipoDocumentos.forEach(tipoDocumento -> {
            int duplicados = Collections.frequency(tipoDocumentos, tipoDocumento);

            if(duplicados > 1){
                throw new MsgException("Não vai ser possível cadastrar este beneficiário pois tem tipo de documento duplicados");
            }
        });
    }

    private void preencherCamposBeneficiario(Beneficiario beneficiarioEncontrado, Beneficiario beneficiario){
        beneficiarioEncontrado.setNome(beneficiario.getNome());
        beneficiarioEncontrado.setTelefone(beneficiario.getTelefone());
        beneficiarioEncontrado.setDataNascimento(beneficiario.getDataNascimento());
    }
}
