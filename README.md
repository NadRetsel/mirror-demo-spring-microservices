# demo-spring-microservices

A Spring Boot demo project to familiarise the microservices architecture.


## microservice-parent

### product-service


- Spring Boot framework
- Apache Maven
- MongoDB
- Testcontainers framework 
  - Used for automated integration tests 
  - Requires Docker - not currently working

### order-service

- Spring Boot framework
- ApacheMaven
- MySQL


### inventory-service

- Spring Boot framework
- Apache Maven
- MySQL


## microservice-new
A Maven multi-module version of the project.


### product-service

- Spring Boot framework
- Apache Maven
- MongoDB
- Testcontainers framework
  - Used for automated integration tests
  - Requires Docker - not currently working
- Netflix Eureka

### order-service

- Spring Boot framework
- ApacheMaven
- MySQL
- Spring Webflux
- Netflix Eureka


### inventory-service

- Spring Boot framework
- Apache Maven
- MySQL
- Netflix Eureka


### discovery-server

- Spring Boot
- Netflix Eureka


### api-gateway

- Netflix Eureka
- Spring Cloud Gateway