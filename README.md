# race-compass

Projeto de Microsserviços com Integrações Específicas

&nbsp;

## Tecnologias Utilizadas:
* Java 17
* Spring Boot 3.1.3
* MongoDB
* Courvege
* OpenFeign
* RabbitMQ
* Docker
* Swagger


&nbsp;

## Requisitos Para Rodar o Projeto:
### Instalar em sua maquina local:
* IDE de sua preferência (indico o Intellij)
* Java 17
* Docker

&nbsp;

## Rode Localmente:
```

git clone https://github.com/Luizaonorio/race-compass.git

```
&nbsp;

## Navegue até o Pasta do Projeto:
```

cd race-compass

```
&nbsp;

## Crie e execute o aplicativo:
```

./mvnw spring-boot:run

```

&nbsp;


As APIs podem serem acessadas em: 

* http://localhost:8088 (ms-cars)
* http://localhost:8089 (ms-history)
* http://localhost:8090 (ms-races)

&nbsp;


## Regras de Negócio:

Microsserviço ms-cars:

* Não podem existir pilotos totalmente iguais.
* Deve haver apenas um CRUD.
* Não podem existir carros totalmente iguais.

&nbsp;


ms-races:

* Deve consumir o ms-cars para obter no máximo 10 carros.
* O aplicativo deve selecionar aleatoriamente de 3 a 10 carros.
* Um carro só pode ultrapassar o carro à sua frente.
* Deve enviar o resultado da corrida para uma fila RabbitMQ.
  

&nbsp;

ms-history:

* Deve consumir a fila do ms-races e salvar no banco de dados.
* Deve incluir a data em que o registro foi inserido no banco de dados.
* Deve fornecer um endpoint para consultar a corrida por ID.
* Deve fornecer um endpoint para consultar todas as corridas.

&nbsp;


## Requisitos:

* Documentação da API; favor especificar a abordagem escolhida.
* Uso do banco MongoDB em um ou mais serviços.
* Uso do OpenFeign.
* Uso do RabbitMQ.

&nbsp;
