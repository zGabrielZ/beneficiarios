# API Beneficiários

# Tecnologias

## Backend
- Java (Versão 17)
- Maven
- Lombok
- Spring Boot
- MapStruct
- JPA/Hibernate
- MockMvc e JUnit
- Banco de dados H2
- Swagger (Documentação)
- Jacoco

## Sobre o projeto

Esta aplicação consiste cadastrar um beneficiário junto com seus documentos, além de cadastrar, é possível buscar beneficiários já cadastrados, atualizar os dados de um beneficiário e também remover. Também podemos buscar uma lista de documentos de cada beneficiário. 
A lista de beneficiários e a lista de documentos por beneficiário é paginado, por padrão mostra cinco elementos por página.

## Cadastro de beneficiário
![Cadastro de beneficiário](https://github.com/zGabrielZ/assets/blob/main/API%20Benefici%C3%A1rio/cadastro-beneficiario.png)

## Lista de beneficiários
![Lista de beneficiários](https://github.com/zGabrielZ/assets/blob/main/API%20Benefici%C3%A1rio/lista-beneficiarios-paginados.png)

## Lista de documentos por beneficiários
![Lista de documentos por beneficiários](https://github.com/zGabrielZ/assets/blob/main/API%20Benefici%C3%A1rio/lista-documentos-beneficiario-paginados.png)

## Modelo conceitual
![Modelo conceitual](https://github.com/zGabrielZ/assets/blob/main/API%20Benefici%C3%A1rio/modelo-conceitual.png)

# Como executar o projeto

## Backend 

Pré requisito: Java 17

```
# clonar o projeto beneficiarios api
git clone https://github.com/zGabrielZ/beneficiarios.git

# entrar na pasta backend do projeto api beneficiarios
cd backend

# rodar a aplicação
./mvnw spring-boot:run
```

Documentação API: http://localhost:8080/swagger-ui/index.html#/

![Documentação API Swagger](https://github.com/zGabrielZ/assets/blob/main/API%20Benefici%C3%A1rio/swagger.png)

Banco de dados H2: http://localhost:8080/h2-console

![Banco de dados H2](https://github.com/zGabrielZ/assets/blob/main/API%20Benefici%C3%A1rio/banco-h2.png)

# Autor

Gabriel Ferreira

https://www.linkedin.com/in/gabriel-ferreira-4b817717b/



