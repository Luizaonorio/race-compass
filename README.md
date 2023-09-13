# race-compass

O Projeto se trata de uma simulação de corrida utilizando microsserviços com Integrações Específicas (ms-cars, ms-races e ms-history), conectadas por openFeign e menssageria (RabbitMQ).


&nbsp;

## Tecnologias Utilizadas:
* Java 17
* Spring Boot 3.1.3
* MongoDB
* Courvege
* OpenFeign
* RabbitMQ
* Docker


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

## Navegue até o Microsserviço:
```

cd ms-cars

```
&nbsp;

## Rode o docker-compose do Microsserviço:
```

docker-compose up -d

```
&nbsp;

## Crie e execute o aplicativo:
```

./mvnw spring-boot:run

```

&nbsp;

Entre e siga os demais passos com os ademais microsserviços de ms-races e ms-history, seguindo a ordem.


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


## Lógica Implementada:

Segui a escalada de desenvolvimento pela seguinte ordem: 

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/70ea4444-3c05-4eb4-acbd-c857a2c010e1)

&nbsp;

Pois, segundo os requisitos, podemos perceber que a dependência que um tem pelo conseguinte. O único que não necessita em nenhuma funcionalidade de outro MS é o ms-cars (por isso o primeiro a ser implementado). Seguindo para ms-races que depende da lista de carros sorteados de cars, e por fim, para ms-history que depende do resultado da corrida ser feito para salva-lá em seu banco. 

! Todos os microsserviços foram implementados com MongoDB.

## Funcionalidades:

### MS-CARS

Conta com apenas um CRUD completo. Portanto, foi definido que o objeto piloto deveria estar dentro de Car. Seguindo o exemplo:
```json
{
        "brand": "String",
        "model": "String",
        "pilot": {
            "name": "String",
            "age": 22
        }, 
        "year": "2023"
}
```
&nbsp;

Também, segue com as solicitações de validação de carro ou piloto já existente.

&nbsp;

### MS-RACES

Este microsserviço conta com duas entidades, a Track que seria o local que a corrida deve acontecer, cujo, deverá ter o seguinte JSON como exemplo:
```json
{
    "name": "String",
    "country": "String"
}
```

E Race, que deverá conter o seguinte JSON, que informa o nome da corrida, a data que irá ocorrer e o id da Track que será o local, assim como o exemplo:
```json
{
    "name": "Cross Country",
    "date": "2002-09-09",
    "track": "64ffbff2104fb4299c2c0f0d"
}
```

&nbsp;

OBS: A funcionalidade de criar e iniciar a corrida são feitas na URL de criar a race, ou seja, após gerar a nova race você também já produz o resultado das posições dos carros. 

Segue com as solicitações de envio de resultado da corrida para uma fila RabbitMQ, ultrapassagem de carros e o máximo de carros consumidos.

Os carros consumidos aleatoriamente são desfrutados do método rafflingOffCars presente em ms-cars e consumido em ms-races po meio do OpenFeign.

&nbsp;

### MS-HISTORY

Apresenta apenas metodos de encontrar history por id ou todas as historys já salvas (findById e findAll), já que, ela apenas consumirá os resultados das corridas de ms-races e salvará no seu banco de dados.

Segue a solicitação de incluir data atual em que o registro foi inserido no banco de dados.


&nbsp;

## Segurança e Qualidade:

#### Testes contam com cobertura de 63%
![image](https://github.com/Luizaonorio/race-compass/assets/102754689/a9befece-9696-4438-8bcc-1a7b8868dfad)
&nbsp;

Ferramenta Coverage foi escolhida para essa análise da pocentagem dos testes.

#### Para rodar analise em sua maquina

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/90546f5d-3892-4107-8d75-28a7980c2fda)
##### Clique no packege de Java sublinhado em amarelo na foto, com o botão direito do seu mouse.

&nbsp;

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/741095a9-7403-4dc3-8ba4-6837e1bdeea5)
##### Após, procure por "More run/ Debbug" e deixe o mouse sob.

&nbsp;

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/c98d4b4f-e94a-46bc-885e-a22b7ede5570)
##### Por fim, procure e clique em "Run 'All Tests' with Coverage".

&nbsp;

&nbsp;

## Estrutura de Código:
#### A estrutura de branch, segue o seguinte padrão:
No início do projeto foram utilizadas as branches fixas "main" e "dev", seguindo o padrão para novas features de dev/(microsserviço).
Após a deleção indevida e acidental da branch "dev", o padrão de branches foi alterado de "dev" para "develop" e novas features mudaram para developed/(microsserviço)


#### A estrutura de commits, segue o seguinte padrão:
prefixo(microsserviço): O que foi feito no commit [issue]

##### Exemplo:
feat(ms-cars): Add CRUD methods [#1]

&nbsp;

## Funcionalidades que serão implementadas futuramente:

Não foram implementados em razão da carêcia de tempo investido e priorização de outras funcionalidades.

* Docker que roda todos os microsserviços juntos.
* Cobertura de testes superior a 70%;
* ApiGateway;
* Segurança.



