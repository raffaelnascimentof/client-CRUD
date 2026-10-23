# CRUD de Clientes

API REST desenvolvida como desafio do curso **Formação Desenvolvedor Moderno** da [DevSuperior](https://devsuperior.com.br), módulo Back end — capítulo de API REST, camadas, CRUD, exceções e validações.

## Tecnologias

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Spring Validation (Bean Validation)
- H2 Database (in-memory)
- Maven

## Como executar

```bash
# Clone o repositório
git clone <url-do-repositorio>

# Entre na pasta do projeto
cd clients-crud

# Execute com o Maven Wrapper
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

O console do H2 fica disponível em `http://localhost:8080/h2-console` com as configurações:
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `sa`
- **Password:** *(vazio)*

## Entidade Client

| Campo       | Tipo        | Descrição                  |
|-------------|-------------|----------------------------|
| id          | Long        | Identificador              |
| name        | String      | Nome do cliente            |
| cpf         | String      | CPF                        |
| income      | Double      | Renda                      |
| birthDate   | LocalDate   | Data de nascimento         |
| children    | Integer     | Quantidade de filhos       |

## Endpoints

### Busca paginada
```
GET /clients?page=0&size=6&sort=name
```

### Busca por id
```
GET /clients/{id}
```

### Inserir cliente
```
POST /clients
Content-Type: application/json

{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
```

### Atualizar cliente
```
PUT /clients/{id}
Content-Type: application/json

{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
```

### Deletar cliente
```
DELETE /clients/{id}
```

## Tratamento de erros

| Situação                                  | Código HTTP |
|-------------------------------------------|-------------|
| Cliente não encontrado (GET, PUT, DELETE) | 404         |
| Dados inválidos (POST, PUT)               | 422         |

### Regras de validação

- `name` — não pode ser vazio
- `birthDate` — não pode ser uma data futura

Exemplo de resposta 422:
```json
{
  "timestamp": "2024-07-20T12:00:00Z",
  "status": 422,
  "error": "Dados inválidos",
  "path": "/clients",
  "errors": [
    { "fieldName": "name", "message": "Nome não pode ser vazio" },
    { "fieldName": "birthDate", "message": "Data de nascimento não pode ser data futura" }
  ]
}
```

## Estrutura do projeto

```
src/main/java/com/devsuperior/clientscrud/
├── controllers/
│   └── ClientController.java
├── dto/
│   ├── ClientDTO.java
│   ├── CustomErrorDTO.java
│   ├── FieldMessageDTO.java
│   └── ValidationErrorDTO.java
├── entities/
│   └── Client.java
├── exceptions/
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repositories/
│   └── ClientRepository.java
├── services/
│   └── ClientService.java
└── ClientsCrudApplication.java
```
