package com.neyvo.desafioSpringBoot.controller;

import com.neyvo.desafioSpringBoot.dto.ProdutoRequestDTO;
import com.neyvo.desafioSpringBoot.dto.ProdutoResponseDTO;
import com.neyvo.desafioSpringBoot.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @Operation(
      summary = "Cadastro de produto",
      description = "Cadastro de produtos",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Operação realizada com sucesso!",
              useReturnTypeSchema = true
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Campo(s) com valor(es) inválido(s) ou a ausência campo(s)"
          ),
          @ApiResponse(
              responseCode = "422",
              description = "Dados inválidos para cadastro do produto"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Erro inesperado do servidor"
          )
      }
  )
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProdutoResponseDTO cadastrarProduto(@RequestBody ProdutoRequestDTO dto) {
    var produto = produtoService.cadastrarProduto(dto);
    return produto;
  }

  @Operation(
      summary = "Busca de todos os produtos",
      description = "Busca por todos os produtos cadastrados usando filtro por nome e paginação",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Operação realizada com sucesso!",
              useReturnTypeSchema = true
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Erro inesperado do servidor"
          )
      },
      parameters = {
          @Parameter(
              name = "nome",
              description = "Filtro por nome do produto"
          ),
          @Parameter(
              name = "pagina",
              description = "Página atual",
              example = "0"
          ),
          @Parameter(
              name = "tamanho",
              description = "Registros por página",
              example = "30"
          )
      }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public Page<ProdutoResponseDTO> listar(@RequestParam(required = false) String nome,
      @RequestParam Integer pagina,
      @RequestParam Integer tamanho) {

    var pageable = PageRequest.of(pagina, tamanho);
    return produtoService.listar(nome,pageable);
  }


  @Operation(
      summary = "Busca um produto",
      description = "Busca produto pelo id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Operação realizada com sucesso!",
              useReturnTypeSchema = true
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Erro inesperado do servidor"
          )
      },
      parameters = {
          @Parameter(
              name = "id",
              description = "Id do produto"
          )
      }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  public ProdutoResponseDTO buscarPorId(@PathVariable Long id)  {

    return produtoService.buscarPorId(id);
  }

  @Operation(
      summary = "Atualiza um produto",
      description = "Atualiza produto pelo id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Operação realizada com sucesso!",
              useReturnTypeSchema = true
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Erro inesperado do servidor"
          )
      },
      parameters = {
          @Parameter(
              name = "id",
              description = "Id do produto"
          )
      }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{id}")
  public ProdutoResponseDTO atualizarPorId(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {

    return produtoService.atualizarPorId(id, dto);
  }

  @Operation(
      summary = "Remove um produto",
      description = "Remove produto pelo id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Operação realizada com sucesso!",
              useReturnTypeSchema = true
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Erro inesperado do servidor"
          )
      },
      parameters = {
          @Parameter(
              name = "id",
              description = "Id do produto"
          )
      }
  )
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{id}")
  public void removerPorId(@PathVariable Long id) {

    produtoService.removePorId(id);
  }

}
