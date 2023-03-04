### Usado no projeto:
- Java 17
- Spring Framework
- Spring Boot 3.0.4
- mysql 8.0.32

### Rotas

criar pessoa [POST]
``
localhost:8080/pessoas
``
```json
{       
    "nome": "pessoa test1",
    "idade": 20 
}
```


Atualizar pessoa [PUT]
``
localhost:8080/pessoas/{id}
``
```json
{       
    "nome": "pessoa test2",
    "idade": 21
}
```

Buscar todas as pessoas [GET]
``
localhost:8080/pessoas
``

Buscar uma pessoa por id [GET]
``
localhost:8080/pessoas/{id}
``
