package com.neyvo.desafioSpringBoot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String nome;
  private String descricao;
  private BigDecimal preco;

  @Column(name = "quantidade_estoque")
  private Long quantidadeEstoque;

  public static class ProdutoBuilder {
    public ProdutoBuilder from (Produto reference) {
      return builder()
          .id(reference.id)
          .nome(reference.nome)
          .descricao(reference.descricao)
          .preco(reference.preco)
          .quantidadeEstoque(reference.quantidadeEstoque);
    }

    public Produto build() {
      return new Produto(id, nome, descricao, preco, quantidadeEstoque);
    }
  }

}
