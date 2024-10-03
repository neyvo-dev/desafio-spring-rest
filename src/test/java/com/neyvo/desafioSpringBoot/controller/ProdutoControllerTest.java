package com.neyvo.desafioSpringBoot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neyvo.desafioSpringBoot.dto.ProdutoRequestDTO;
import com.neyvo.desafioSpringBoot.service.ProdutoService;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ProdutoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setup() {
    var produtoA = new ProdutoRequestDTO("Produto A", "Descrição do Produto A", new BigDecimal("100"), 10L);
    var produtoB = new ProdutoRequestDTO("Produto B", "Descrição do Produto B", new BigDecimal("200"), 20L);
    var produtoC = new ProdutoRequestDTO("Produto C", "Descrição do Produto C", new BigDecimal("300"), 30L);
    produtoService.cadastrarProduto(produtoA);
    produtoService.cadastrarProduto(produtoB);
    produtoService.cadastrarProduto(produtoC);
  }

  @Test
  void cadastrarProduto() throws Exception {

    var produtoD = objectMapper.writeValueAsString(new ProdutoRequestDTO("Produto D", "Descrição do Produto D", new BigDecimal("400"), 40L));
    mockMvc.perform(post("/produtos")
            .contentType("application/json")
            .content(produtoD))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.nome").value("Produto D"))
        .andExpect(jsonPath("$.preco").value("400"));

  }

  @Test
  void cadastrarProdutoErro400() throws Exception {

    var produtoD = "{ 'nome': 'erro'}";
    mockMvc.perform(post("/produtos")
            .contentType("application/json")
            .content(produtoD))
        .andExpect(status().isBadRequest());

  }

  @Test
  void listar() throws Exception {
    mockMvc.perform(get("/produtos?pagina=0&tamanho=10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].nome").value("Produto A"))
        .andExpect(jsonPath("$.content.[1].nome").value("Produto B"));
  }

  @Test
  void buscarComFiltro() throws Exception {
    mockMvc.perform(get("/produtos?nome=Produto A&pagina=0&tamanho=10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].nome").value("Produto A"));

  }

  @Test
  void buscarPorId() throws Exception {
    mockMvc.perform(get("/produtos/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("Produto A"))
        .andExpect(jsonPath("$.preco").value("100.0"));
  }

  @Test
  void buscarPorIdNaoEncontrado() throws Exception {
    mockMvc.perform(get("/produtos/100"))
        .andExpect(status().isNotFound());
  }

  @Test
  void atualizarPorId() throws Exception {
    var produtoD = objectMapper.writeValueAsString(new ProdutoRequestDTO("Produto D", "Descrição do Produto D", new BigDecimal("400"), 40L));
    mockMvc.perform(put("/produtos/2")
            .contentType("application/json")
            .content(produtoD))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("Produto D"))
        .andExpect(jsonPath("$.preco").value("400"));
  }

  @Test
  void atualizarPorIdNaoEncontrado() throws Exception {
    var produtoD = objectMapper.writeValueAsString(new ProdutoRequestDTO("Produto D", "Descrição do Produto D", new BigDecimal("400"), 40L));
    mockMvc.perform(put("/produtos/300")
        .contentType("application/json")
        .content(produtoD))
        .andExpect(status().isNotFound());
  }

  @Test
  void removerPorId() throws Exception {
    mockMvc.perform(delete("/produtos/3"))
        .andExpect(status().isOk());
  }

  @Test
  void removerPorIdNaoEncontrado() throws Exception {
    mockMvc.perform(delete("/produtos/300"))
        .andExpect(status().isNotFound());
  }
}