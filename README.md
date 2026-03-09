# Forum Hub API

API REST para gerenciamento de tópicos de um fórum de cursos.
O projeto permite que usuários autenticados criem, listem, atualizem e desativem tópicos, utilizando autenticação baseada em JWT.

Este projeto foi desenvolvido como parte do curso de **Spring Framework** da Alura dentro do programa **Oracle Next Education (ONE)**.

---

# Tecnologias utilizadas

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* MySQL
* Flyway (migrations de banco)
* Maven
* Postman (testes da API)

---

# Funcionalidades

* Autenticação de usuário com JWT
* Criação de tópicos
* Listagem de tópicos
* Detalhamento de tópico por ID
* Atualização de tópico
* Exclusão lógica (soft delete) de tópicos
* Proteção de rotas com Spring Security

---

# Autenticação

A API utiliza autenticação com **JWT**.

Para acessar rotas protegidas:

1. Faça login na rota `/login`
2. Copie o token retornado
3. Envie o token no header das próximas requisições

Header:

Authorization: Bearer SEU_TOKEN

---

# Endpoints principais

## Login

POST /login

Body:

```json
{
  "login": "usuario",
  "senha": "senha"
}
```

Resposta:

```json
{
  "token": "jwt_token_aqui"
}
```

---

## Listar tópicos

GET /topicos

Retorna todos os tópicos ativos.

---

## Buscar tópico por ID

GET /topicos/{id}

Retorna os detalhes de um tópico específico.

---

## Criar tópico

POST /topicos

Exemplo de body:

```json
{
  "titulo": "Dúvida sobre Spring Boot",
  "mensagem": "Como funciona o Spring Security?",
  "autor": "Matheus",
  "curso": "Spring Boot"
}
```

---

## Atualizar tópico

PUT /topicos/{id}

Permite atualizar informações de um tópico existente.

---

## Deletar tópico (soft delete)

DELETE /topicos/{id}

O tópico não é removido do banco, apenas marcado como **inativo**.

---

# Banco de dados

A aplicação utiliza **MySQL** como banco de dados.

As migrations do banco são gerenciadas com **Flyway**, garantindo versionamento do schema.

---

# Como executar o projeto

## 1 - Clonar o repositório

```bash
git clone https://github.com/seu-usuario/forum_hub_api.git
```

## 2 - Configurar banco de dados

Criar um banco MySQL:

```
forum_hub
```

Configurar no `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost/forum_hub
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

---

## 3 - Rodar a aplicação

Você pode executar:

* Pela IDE (IntelliJ / Eclipse / VSCode)
* Ou via Maven:

```bash
mvn spring-boot:run
```

---

# Testando a API

A API pode ser testada utilizando ferramentas como:

* Postman
* Insomnia
* Thunder Client

Fluxo recomendado:

1. Criar usuário no banco
2. Fazer login em `/login`
3. Copiar o token JWT
4. Utilizar o token nas rotas protegidas

---

# Estrutura do projeto

```
controller
service
repository
model
dto
security
```

---

# Projeto educacional

Este projeto foi desenvolvido para fins de estudo durante a formação em **Spring Boot** da Alura dentro do programa **Oracle Next Education (ONE)**.

---

# Autor

Matheus Nascimento Guedes
