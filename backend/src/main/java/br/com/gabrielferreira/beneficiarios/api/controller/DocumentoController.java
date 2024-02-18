package br.com.gabrielferreira.beneficiarios.api.controller;

import br.com.gabrielferreira.beneficiarios.api.dto.DocumentoDTO;
import br.com.gabrielferreira.beneficiarios.api.mapper.DocumentoMapper;
import br.com.gabrielferreira.beneficiarios.domain.model.Documento;
import br.com.gabrielferreira.beneficiarios.domain.service.DocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Documento Controller", description = "Endpoints para realizar requisições de documento do beneficiário")
@RestController
@RequestMapping("/beneficiarios/{idBeneficiario}/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final DocumentoService documentoService;

    private final DocumentoMapper documentoMapper;

    @Operation(summary = "Buscar documentos paginados por beneficiário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documentos encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DocumentoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<DocumentoDTO>> buscarDocumentosPorBeneficiarioPaginados(@PathVariable Long idBeneficiario,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size){
        Page<Documento> documentos = documentoService.buscarDocumentosPorBeneficiarioPaginados(idBeneficiario, page, size);
        Page<DocumentoDTO> documentoDTOS = documentoMapper.toDocumentosDtos(documentos);

        return ResponseEntity.ok().body(documentoDTOS);
    }
}
