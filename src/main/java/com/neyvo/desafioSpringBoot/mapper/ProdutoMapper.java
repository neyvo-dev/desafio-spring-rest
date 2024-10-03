package com.neyvo.desafioSpringBoot.mapper;

import com.neyvo.desafioSpringBoot.dto.ProdutoRequestDTO;
import com.neyvo.desafioSpringBoot.dto.ProdutoResponseDTO;
import com.neyvo.desafioSpringBoot.model.Produto;


public class ProdutoMapper {

  public static ProdutoResponseDTO toDTO(Produto produto) {
    return new ProdutoResponseDTO(
        produto.getId(),
        produto.getNome(),
        produto.getDescricao(),
        produto.getPreco(),
        produto.getQuantidadeEstoque()
    );
  }

  public static Produto toEntity(ProdutoRequestDTO dto) {
    return Produto.builder()
        .nome(dto.nome())
        .descricao(dto.descricao())
        .preco(dto.preco())
        .quantidadeEstoque(dto.quantidadeEstoque())
        .build();
  }


  public static Produto toEntityUpdated(ProdutoRequestDTO dto, Produto produtoPesquisado) {
    return Produto.builder()
        .from(produtoPesquisado)
        .nome(dto.nome())
        .descricao(dto.descricao())
        .preco(dto.preco())
        .quantidadeEstoque(dto.quantidadeEstoque())
        .build();
  }
}
