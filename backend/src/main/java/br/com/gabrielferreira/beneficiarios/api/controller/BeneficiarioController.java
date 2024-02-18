package br.com.gabrielferreira.beneficiarios.api.controller;

import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import br.com.gabrielferreira.beneficiarios.api.mapper.BeneficiarioMapper;
import br.com.gabrielferreira.beneficiarios.domain.model.Beneficiario;
import br.com.gabrielferreira.beneficiarios.domain.service.BeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Tag(name = "Beneficiário Controller", description = "Endpoints para realizar requisições de beneficiários")
@RestController
@RequestMapping("/beneficiarios")
@RequiredArgsConstructor
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    private final BeneficiarioMapper beneficiarioMapper;

    @Operation(summary = "Cadastrar beneficiário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiário cadastrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiarioDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<BeneficiarioDTO> cadastrarBeneficiario(@Valid @RequestBody BeneficiarioCreateDTO beneficiarioCreateDTO){
        Beneficiario beneficiario = beneficiarioMapper.toBeneficiario(beneficiarioCreateDTO);
        Beneficiario beneficiarioCadastrado = beneficiarioService.cadastrar(beneficiario);
        BeneficiarioDTO beneficiarioDTO = beneficiarioMapper.toBeneficiarioDto(beneficiarioCadastrado);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(beneficiarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(beneficiarioDTO);
    }
}
