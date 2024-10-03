package com.neyvo.desafioSpringBoot.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProdutoTest {

  @Test
  void deveCriarProduto() {
    Produto produto = Produto.builder()
        .id(1L)
        .nome("Produto A")
        .descricao("Descrição do produto")
        .preco(new BigDecimal(1000))
        .quantidadeEstoque(200L)
        .build();

    assertEquals(produto.getNome(), "Produto A");
  }

}