# Desafio Spring Boot para gerenciamento de produtos

Teste de Desenvolvimento: Aplicacao Rest com Spring Boot

Contexto:
Você foi contratado para desenvolver uma aplicação REST simples que
gerencie informações sobre produtos. A aplicação deve permitir a criação,
atualização, remoção e consulta de produtos.
Requisitos Funcionais:
1. A aplicação deve permitir a criação de um novo produto com os
   seguintes dados: nome, descrição, preço e quantidade em estoque.
2. A aplicação deve permitir a atualização dos dados de um produto
   existente.
3. A aplicação deve permitir a remoção de um produto existente.
4. A aplicação deve permitir a consulta de todos os produtos
   cadastrados, bem como a consulta de um produto específico por ID.
5. Todos os endpoints devem seguir as boas práticas RESTful.

## Requisitos

- **JDK 17** ou superior
- **Maven** (para gerenciar dependências e executar o projeto)

## Configurações do Banco de Dados

Este projeto utiliza o banco de dados H2. 

- **URL do console H2**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:produtodb`
- **Username**: `sa`
- **Password**: `password`


## Executando o Projeto

### 1. Clonar o Repositório

Clone este repositório para a sua máquina local usando o seguinte comando no terminal:

```bash
git clone https://github.com/neyvo-dev/desafio-spring-boot.git
