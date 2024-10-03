package com.neyvo.desafioSpringBoot.dao;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DAO<T> {
  Page<T> findAll(String filtro, Pageable pageable);
  Page<T> findAll(Pageable pageable);
  Optional<T> findById(Long id);
  T save(T t);
  void deleteById(Long id);
}
