package br.com.gabrielferreira.beneficiarios.api.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DocumentoControllerIntegrationTest {

    private static final String URL = "/beneficiarios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    private Long idBeneficiarioExistente;

    private Long idBeneficiarioInexistente;

    @BeforeEach
    void setUp(){
        idBeneficiarioExistente = 1L;
        idBeneficiarioInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar documentos paginados por usuário quando existir usuário")
    @Order(1)
    void deveBuscarDocumentosPaginados() throws Exception {
        String filtro = URL.concat("/").concat(idBeneficiarioExistente.toString()).concat("/documentos").concat("?page=0&size=5");

        ResultActions resultActions = mockMvc
                .perform(get(filtro)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());
    }

    @Test
    @DisplayName("Não deve buscar documentos paginados por usuário quando não existir usuário")
    @Order(2)
    void naoDeveBuscarDocumentosPaginados() throws Exception {
        String filtro = URL.concat("/").concat(idBeneficiarioInexistente.toString()).concat("/documentos").concat("?page=0&size=5");

        ResultActions resultActions = mockMvc
                .perform(get(filtro)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Beneficiário não encontrado"));
    }
}
