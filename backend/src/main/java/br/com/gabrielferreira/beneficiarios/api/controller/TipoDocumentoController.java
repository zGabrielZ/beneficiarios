package br.com.gabrielferreira.beneficiarios.api.controller;

import br.com.gabrielferreira.beneficiarios.api.dto.TipoDocumentoDTO;
import br.com.gabrielferreira.beneficiarios.api.mapper.TipoDocumentoMapper;
import br.com.gabrielferreira.beneficiarios.domain.model.enums.TipoDocumentoEnum;
import br.com.gabrielferreira.beneficiarios.domain.service.TipoDocumentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tipo Documento Controller", description = "Endpoints para realizar requisições de tipo de documento")
@RestController
@RequestMapping("/tipos-documentos")
@RequiredArgsConstructor
public class TipoDocumentoController {

    private final TipoDocumentoService tipoDocumentoService;

    private final TipoDocumentoMapper tipoDocumentoMapper;

    @Operation(summary = "Buscar tipos de documentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de documentos encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoDocumentoDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<List<TipoDocumentoDTO>> buscarTiposDocumentos(){
        List<TipoDocumentoEnum> tipoDocumentoEnums = tipoDocumentoService.buscarTipoDocumentos();
        List<TipoDocumentoDTO> tipoDocumentoDTOS = tipoDocumentoMapper.toTipoDocumentosDtos(tipoDocumentoEnums);

        return ResponseEntity.ok().body(tipoDocumentoDTOS);
    }
}
