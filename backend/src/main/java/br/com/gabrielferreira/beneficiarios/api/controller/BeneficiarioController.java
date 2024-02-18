package br.com.gabrielferreira.beneficiarios.api.controller;

import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.BeneficiarioResumidoDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.update.BeneficiarioUpdateDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "Buscar beneficiários paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiários encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiarioResumidoDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<Page<BeneficiarioResumidoDTO>> buscarBeneficiariosPaginados(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                                      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size){
        Page<Beneficiario> beneficiarios = beneficiarioService.buscarBeneficiariosPaginados(page, size);
        Page<BeneficiarioResumidoDTO> beneficiarioResumidoDTOS = beneficiarioMapper.toBeneficiariosResumidoDtos(beneficiarios);

        return ResponseEntity.ok().body(beneficiarioResumidoDTOS);
    }

    @Operation(summary = "Deletar beneficiário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beneficiário deletado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBeneficiarioPorId(@PathVariable Long id){
        beneficiarioService.deletarBeneficiarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar beneficiário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário atualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiarioDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Regra de negócio",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioResumidoDTO> atualizarBeneficiario(@PathVariable Long id, @Valid @RequestBody BeneficiarioUpdateDTO beneficiarioUpdateDTO){
        Beneficiario beneficiario = beneficiarioMapper.toBeneficiario(beneficiarioUpdateDTO);
        Beneficiario beneficiarioAtualizado = beneficiarioService.atualizarBeneficiario(id, beneficiario);
        BeneficiarioResumidoDTO beneficiarioResumidoDTO = beneficiarioMapper.toBeneficiarioResumidoDto(beneficiarioAtualizado);

        return ResponseEntity.ok().body(beneficiarioResumidoDTO);
    }
}
