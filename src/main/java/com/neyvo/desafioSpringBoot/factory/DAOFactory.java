package com.neyvo.desafioSpringBoot.factory;

import com.neyvo.desafioSpringBoot.dao.DAO;
import com.neyvo.desafioSpringBoot.model.Produto;
import com.neyvo.desafioSpringBoot.repository.ProdutoDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DAOFactory {

  @Autowired
  private ProdutoDAOImpl produtoDAO;

  public DAO<?> getDAO(Class<?> clazz) {
    if(clazz == Produto.class) {
      return produtoDAO;
    }
    throw new IllegalArgumentException("DAO não encontrada para a classe: "+clazz.getSimpleName());
  }

}
