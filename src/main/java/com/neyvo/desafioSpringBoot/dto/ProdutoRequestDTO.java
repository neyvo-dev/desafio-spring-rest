package com.neyvo.desafioSpringBoot.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
    String nome,
    String descricao,
    BigDecimal preco,
    Long quantidadeEstoque
) {

}
