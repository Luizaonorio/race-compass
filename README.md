# race-compass

The project is a race simulation using microservices with Specific Integrations (ms-cars, ms-races, and ms-history), connected by openFeign and messaging (RabbitMQ).

&nbsp;

## Technologies Used:
* Java 17
* Spring Boot 3.1.3
* MongoDB
* Coverage
* OpenFeign
* RabbitMQ
* Docker
* Swagger


&nbsp;

## Requirements to Run the Project:
### Install on your local machine:
* IDE of your choice (I recommend IntelliJ)
* Java 17
* Docker

&nbsp;

## Run Locally:
```shell

git clone https://github.com/Luizaonorio/race-compass.git

```
&nbsp;

## Navigate to the Project Folder:
```shell

cd race-compass

```
&nbsp;

## Navigate to the Microservice:
```shell

cd ms-cars

```
&nbsp;

## Run the Microservice's Docker Compose:
```shell

docker-compose up -d

```
&nbsp;

## Create and Run the Application:
```shell

./mvnw spring-boot:run

```

&nbsp;

##### ! Follow the same steps for the other microservices ms-races and ms-history, starting from "Navigate to the Microservice".

&nbsp;

You can access the APIs at:

* http://localhost:8088 (ms-cars)
* http://localhost:8089 (ms-history)
* http://localhost:8090 (ms-races)

&nbsp;


## Business Rules:

Microservice ms-cars:

* There cannot be completely identical pilots.
* There should be only one CRUD.
* There cannot be completely identical cars.

&nbsp;


ms-races:

* Must consume ms-cars to get a maximum of 10 cars.
* The application must randomly select from 3 to 10 cars.
* A car can only overtake the car in front of it.
* Must send the race result to a RabbitMQ queue.
  

&nbsp;

ms-history:

* Must consume the ms-races queue and save it to the database.
* Must include the date the record was inserted into the database.
* Must provide an endpoint to query the race by ID.
* Must provide an endpoint to query all races.

&nbsp;


&nbsp;

## Documentation


### Implemented Logic:

I followed the development progression in the following order:

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/bb6905dc-104f-467b-b189-118c55406022)



&nbsp;

Because, according to the requirements, we can see the dependency that one has on the other. The only one that does not need any functionality from another MS is ms-cars (that's why it was implemented first). Moving on to ms-races, which depends on the list of randomly selected cars from ms-cars, and finally to ms-history, which depends on the race result being generated to save it in its database.

! All microservices were implemented with MongoDB.

### Features:

#### MS-CARS

It has only one complete CRUD. Therefore, it was defined that the pilot object should be inside Car. Following the example:
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

It also includes validation requests for existing cars or pilots.

&nbsp;

#### MS-RACES

This microservice has two entities, Track, which is the location where the race should take place, and should have the following JSON as an example:
```json
{
    "name": "String",
    "country": "String"
}
```

And Race, which should contain the following JSON, which informs the name of the race, the date it will take place, and the id of the Track, which will be the location, as in the example:
```json
{
    "name": "Cross Country",
    "date": "2002-09-09",
    "track": "64ffbff2104fb4299c2c0f0d"
}
```

&nbsp;

Note: The functionality of creating and starting the race is done in the URL to create the race, meaning that after generating the new race, you also produce the race result.

Includes requests for sending race results to a RabbitMQ queue, car overtaking, and maximum cars consumed.

The randomly consumed cars are enjoyed from the rafflingOffCars method present in ms-cars and consumed in ms-races through OpenFeign.

&nbsp;

#### MS-HISTORY

It only includes methods to find history by ID or all saved history (findById and findAll), as it only consumes race results from ms-races and saves them in its database.

Includes the request to include the current date when the record was inserted into the database.


&nbsp;

### Security and Quality:

#### Tests have a coverage of 74%
![image](https://github.com/Luizaonorio/race-compass/assets/102754689/7108bffa-9c93-478f-aa8a-c4f9e9e025dc)

&nbsp;

The Coverage tool was chosen for this test coverage percentage analysis.

#### To Run the Analysis on your Machine

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/90546f5d-3892-4107-8d75-28a7980c2fda)
##### Click on the underlined yellow Java package in the photo with your right mouse button.

&nbsp;

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/741095a9-7403-4dc3-8ba4-6837e1bdeea5)
##### After that, look for "More run/ Debug" and hover your mouse over it.

&nbsp;

![image](https://github.com/Luizaonorio/race-compass/assets/102754689/c98d4b4f-e94a-46bc-885e-a22b7ede5570)
##### Finally, look for and click on "Run 'All Tests' with Coverage".

&nbsp;

&nbsp;

### Code Structure:
#### The branch structure follows the following pattern:
At the beginning of the project, the fixed branches "main" and "dev" were used, following the pattern for new features of dev/(microservice). After the accidental and unintended deletion of the "dev" branch, the branch pattern was changed from "dev" to "develop," and new features were changed to "developed/(microservice)"
Após a deleção indevida e acidental da branch "dev", o padrão de branches foi alterado de "dev" para "develop" e novas features mudaram para developed/(microsserviço)


#### The commit structure follows the following pattern:
prefix(microservice): What was done in the commit [issue]

##### Example:
feat(ms-cars): Add CRUD methods [#1]

&nbsp;

### API Documentation

Swagger was used for API documentation. You can access the microservices through the following URLs:

&nbsp;

#### MS-Cars
http://localhost:8088/swagger-ui/index.html#/

#### MS-Races
http://localhost:8088/swagger-ui/index.html#/

#### MS-History
http://localhost:8089/swagger-ui/index.html#/

&nbsp;

! You can find valid objects for Track, Race, and Cars in "Implemented Logic".

&nbsp;

## Features to be Implemented in the Future:

These were not implemented due to a shortage of invested time and prioritization of other functionalities:

* Docker that runs all microservices together.
* ApiGateway;
* Security.




