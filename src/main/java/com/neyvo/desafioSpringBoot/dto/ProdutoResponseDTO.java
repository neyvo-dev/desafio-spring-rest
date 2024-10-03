package com.neyvo.desafioSpringBoot.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    Long quantidadeEstoque
) {

}
