# API do Banco de Oportunidades Upswing
O Upswing é uma plataforma projetada para facilitar o ingresso de estudantes no mercado de trabalho de forma mais assertiva. Desenvolvido para instituições de ensino, o Upswing oferece três perfis principais: administrador, aluno e empresa, com foco principal no aluno.

## Tecnologias Utilizadas:
#### Java 17
#### Spring Boot (Web, Validation, JPA, Security, Mail)
#### Banco de Dados PostgreSQL
#### JSON Web Token (JWT)
#### Flyway
#### Swagger

## Design Patterns Utilizados:
#### Strategy
#### Template Method
#### Null Object
#### Data Transfer Object

## Pré-requisitos
#### Java 17
#### Maven
#### PostgreSQL 16

## Instalação e execução
1. Clone este repositorio
2. Certifique-se de está utilizando JDK 17, Maven 3.x e PostgreSQL 16
3. Configure a conexão com o banco de dados de acordo com as configurações do seu banco. Acesse o arquivo **application.properties** no diretorio **src/main/resources**
4. Você pode fazer o Build do projeto executando o comando `mvn clean package`
5. Depois do Build ter sido executado com sucesso você pode executa-lo com o comando `java -jar target/upswing-0.0.1-SNAPSHOT.jar`. Este comando fará com que a aplicação rode localmente em sua máquina na porta *8080*

### Para visualizar a documentação da API no Swagger 3
Com o projeto rodando em sua máquina acesse em seu navegador a URL `localhost:8080/swagger-ui.html`

## Integração do Back End com Front End
Para integrar a API do Upswing ao Front do projeto basta acessar o repositorio `https://github.com/Flickler/upswing`

## Contribuição
O Banco de Oportunidades Upswing é um projeto que ainda está em desenvolvimento e estamos abertos a contribuições. Se você se interessou pela ideia e queira contribuir com o projeto basta entrar em contato com a gente pelos links abaixo:

### Contato
