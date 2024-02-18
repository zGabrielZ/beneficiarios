package br.com.gabrielferreira.beneficiarios.api.controller;

import br.com.gabrielferreira.beneficiarios.api.dto.create.BeneficiarioCreateDTO;
import br.com.gabrielferreira.beneficiarios.api.dto.create.DocumentoCreateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static br.com.gabrielferreira.beneficiarios.tests.BeneficiarioFactory.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeneficiarioControllerIntegrationTest {

    private static final String URL = "/beneficiarios";
    private static final MediaType MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private BeneficiarioCreateDTO beneficiarioCreateDTO;

    private Long idBeneficiarioExistente;

    private Long idBeneficiarioInexistente;

    @BeforeEach
    void setUp(){
        beneficiarioCreateDTO = criarBeneficiario();
        idBeneficiarioExistente = 1L;
        idBeneficiarioInexistente = -1L;
    }

    @Test
    @DisplayName("Deve cadastrar beneficiário quando informar dados")
    @Order(1)
    void deveCadastrarBeneficiarioQuandoInformarDados() throws Exception{
        String jsonBody = objectMapper.writeValueAsString(beneficiarioCreateDTO);

        String nomeEsperado = beneficiarioCreateDTO.getNome();
        String telefoneEsperado = beneficiarioCreateDTO.getTelefone();
        String dataNascimentoEsperado = beneficiarioCreateDTO.getDataNascimento().toString();

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.id").exists());
        resultActions.andExpect(jsonPath("$.nome").value(nomeEsperado));
        resultActions.andExpect(jsonPath("$.telefone").value(telefoneEsperado));
        resultActions.andExpect(jsonPath("$.dataNascimento").value(dataNascimentoEsperado));
        resultActions.andExpect(jsonPath("$.documentos").exists());
        resultActions.andExpect(jsonPath("$.dataInclusao").exists());
    }

    @Test
    @DisplayName("Não deve cadastrar beneficiário quando não informar dados")
    @Order(2)
    void naoDeveCadastrarBeneficiarioQuandoNaoInformarDados() throws Exception{
        beneficiarioCreateDTO.setNome(null);
        beneficiarioCreateDTO.setTelefone(null);
        beneficiarioCreateDTO.setDataNascimento(null);
        beneficiarioCreateDTO.setDocumentos(null);

        String jsonBody = objectMapper.writeValueAsString(beneficiarioCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Erro validação de campos"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Ocorreu um erro de validação nos campos"));
        resultActions.andExpect(jsonPath("$.campos").exists());
    }

    @Test
    @DisplayName("Não deve cadastrar beneficiário quando informar telefone inválido")
    @Order(3)
    void naoDeveCadastrarBeneficiarioQuandoInformarTelefoneInvalido() throws Exception{
        beneficiarioCreateDTO.setTelefone("1111111111111111111");

        String jsonBody = objectMapper.writeValueAsString(beneficiarioCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("O tipo de telefone está incorreto. Exemplo: (99) 9999-9999 ou (99) 99999-9999"));
    }

    @Test
    @DisplayName("Não deve cadastrar beneficiário quando informar tipos de documentos repetidos")
    @Order(4)
    void naoDeveCadastrarBeneficiarioQuandoInformarTipoDocumentosRepetidos() throws Exception{
        DocumentoCreateDTO documento1 = beneficiarioCreateDTO.getDocumentos().get(0);
        DocumentoCreateDTO documento2 = beneficiarioCreateDTO.getDocumentos().get(0);
        beneficiarioCreateDTO.setDocumentos(Arrays.asList(documento1, documento2));

        String jsonBody = objectMapper.writeValueAsString(beneficiarioCreateDTO);

        ResultActions resultActions = mockMvc
                .perform(post(URL)
                        .content(jsonBody)
                        .contentType(MEDIA_TYPE_JSON)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andExpect(jsonPath("$.titulo").value("Regra de negócio"));
        resultActions.andExpect(jsonPath("$.mensagem").value("Não vai ser possível cadastrar este beneficiário pois tem tipo de documento duplicados"));
    }

    @Test
    @DisplayName("Deve buscar beneficiários paginados")
    @Order(5)
    void deveBuscarBeneficiariosPaginados() throws Exception {
        String filtro = URL.concat("?page=0&size=5");

        ResultActions resultActions = mockMvc
                .perform(get(filtro)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.content").exists());
    }

    @Test
    @DisplayName("Deve deletar beneficiário quando existir dados")
    @Order(6)
    void deveDeletarBeneficiarioQuandoExistirDados() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idBeneficiarioExistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Não deve deletar beneficiário quando não existir dados")
    @Order(7)
    void naoDeveDeletarBeneficiario() throws Exception {
        ResultActions resultActions = mockMvc
                .perform(delete(URL.concat("/{id}"), idBeneficiarioInexistente)
                        .accept(MEDIA_TYPE_JSON));

        resultActions.andExpect(status().isNotFound());
        resultActions.andExpect(jsonPath("$.mensagem").value("Beneficiário não encontrado"));
    }
}
