# Star Wars Resistence Social Network

### Requisitos

- Java 8

### Execução via Maven

```sh
./mvnw clean spring-boot:run
```

### Execução via .jar

```sh
./mvnw clean package

cd target

java -jar network-api-1.0.0.jar
```

### Documentação OpenAPI / Swagger

http://localhost:8080/api/v1/swagger-ui/index.html

### Acesso ao console do H2

http://localhost:8080/api/v1/h2-console

- **JDBC URL:** jdbc:h2:file:./data/starwars
- **Username:** star
- **Password:** wars

### Coleção Postman

Arquivo **network-api.json** disponível na raíz do projeto

