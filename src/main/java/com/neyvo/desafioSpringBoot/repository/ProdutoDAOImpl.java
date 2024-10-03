package com.neyvo.desafioSpringBoot.repository;

import com.neyvo.desafioSpringBoot.dao.DAO;
import com.neyvo.desafioSpringBoot.model.Produto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDAOImpl implements DAO<Produto> {

  @Autowired
  private ProdutoJpaRepository produtoJpaRepository;

  @Override
  public Page<Produto> findAll(String nome, Pageable pageable) {
    return produtoJpaRepository.findByNomeContainingIgnoreCase(nome, pageable);
  }

  @Override
  public Page<Produto> findAll(Pageable pageable) {
    return produtoJpaRepository.findAll(pageable);
  }

  @Override
  public Optional<Produto> findById(Long id) {
    return produtoJpaRepository.findById(id);
  }

  @Override
  public Produto save(Produto produto) {
    return produtoJpaRepository.save(produto);
  }

  @Override
  public void deleteById(Long id) {
    produtoJpaRepository.deleteById(id);

  }
}
