
# microservice-new
A Maven multi-module version of the project to familiarise with the microservices architecture.

**Pre-requisites:**
- JDK 17+
- Apache Maven
- MongoDB
- MySQL
- Keycloak (requires JDK 17+)


## product-service
Service to request products.

- Spring Boot framework
- Apache Maven
- MongoDB
- Testcontainers framework
  - Used for automated integration tests
  - Requires Docker - not currently working
- Spring Cloud Netflix Eureka
  - Used for service discovery

## order-service
Service to place orders of products.

- Spring Boot framework
- Apache Maven
- MySQL
- Spring Webflux
- Spring Cloud Netflix Eureka


## inventory-service
Service to check inventory of products.

- Spring Boot framework
- Apache Maven
- MySQL
- Spring Cloud Netflix Eureka


## discovery-server
Service to find IP address of multiple instances of other services.

- Apache Maven
- Spring Cloud Netflix Eureka
- Spring Boot Security


## api-gateway
Service to route requests to the corresponding service.

- Apache Maven
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Keycloak 
  - Used for security authorisation