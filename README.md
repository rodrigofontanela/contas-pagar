## Contas a Pagar

### Quick start

Executando localmente o projeto.

#### Pré-requisitos
* [JDK 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) 
* [Docker](https://docs.docker.com/) 
* [Docker-Compose](https://docs.docker.com/compose/)  


#### Iniciando dependências

Todos os serviços dos quais a API depende foram configurados no arquivo ``docker-compose``, que pode ser encontrado na raiz do projeto. Acesse este diretório e execute o comando abaixo para iniciar todas as dependências localmente e em *background*.

```properties
docker-compose up -d
```

Em seguida, acesse um client SQL de sua preferência ou ``pgadmin`` que já está incluído no ``docker-compose``:

```properties
http://localhost/login
```

Use as seguintes configurações de conexão:

```properties
Host: <IP Docker> 
Port: 5432 
Database: contas_pagar
Authentication: Database native
Username: suser_mgm
Password: suser_mgm
```

No client SQL, execute o arquivo:
 
 ```
 db\changelog\migrations\init.sql
```

###### O deploy da aplicação e o seu uso ainda está em construção. 



### Construído com

- [Spring Boot](http://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org)
- [Docker](https://www.docker.com)
- [Liquibase](https://www.liquibase.org)


