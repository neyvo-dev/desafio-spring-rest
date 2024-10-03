package com.neyvo.desafioSpringBoot.repository;

import com.neyvo.desafioSpringBoot.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoJpaRepository extends ListCrudRepository<Produto, Long>,
    PagingAndSortingRepository<Produto, Long> {

  Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
