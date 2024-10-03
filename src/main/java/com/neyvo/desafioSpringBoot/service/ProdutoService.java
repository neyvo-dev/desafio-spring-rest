package com.neyvo.desafioSpringBoot.service;

import com.neyvo.desafioSpringBoot.dao.DAO;
import com.neyvo.desafioSpringBoot.dto.ProdutoRequestDTO;
import com.neyvo.desafioSpringBoot.dto.ProdutoResponseDTO;
import com.neyvo.desafioSpringBoot.exception.NaoEncontradoException;
import com.neyvo.desafioSpringBoot.factory.DAOFactory;
import com.neyvo.desafioSpringBoot.mapper.ProdutoMapper;
import com.neyvo.desafioSpringBoot.model.Produto;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

  private final DAO<Produto> produtoDAO;

  @Autowired
  public ProdutoService(DAOFactory daoFactory) {
    this.produtoDAO = (DAO<Produto>) daoFactory.getDAO(Produto.class);
  }

  public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto) {
    var produto = ProdutoMapper.toEntity(dto);
    return ProdutoMapper.toDTO(produtoDAO.save(produto));
  }

  public Page<ProdutoResponseDTO> listar(String nome, Pageable pageable) {
    if(Objects.nonNull(nome)) {
      return produtoDAO.findAll(nome, pageable).map(ProdutoMapper::toDTO);
    }
    return produtoDAO.findAll(pageable).map(ProdutoMapper::toDTO);
  }

  public ProdutoResponseDTO buscarPorId(Long id) {
    var produto = produtoDAO.findById(id).orElseThrow(
        () -> new NaoEncontradoException(String.format("Produto não encontrado com o id: [%s]", id))
    );
    return ProdutoMapper.toDTO(produto);
  }

  public ProdutoResponseDTO atualizarPorId(Long id, ProdutoRequestDTO dto) {
    var produtoPesquisado = produtoDAO.findById(id).orElseThrow(
        () -> new NaoEncontradoException(String.format("Produto não encontrado com o id: [%s]", id))
    );

    var produto = ProdutoMapper.toEntityUpdated(dto, produtoPesquisado);

    return ProdutoMapper.toDTO(produtoDAO.save(produto));
  }

  public void removePorId(Long id) {
    produtoDAO.findById(id).orElseThrow(
        () -> new NaoEncontradoException(String.format("Produto não encontrado com o id: [%s]", id))
    );
    produtoDAO.deleteById(id);
  }
}
