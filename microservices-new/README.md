
# microservice-new
A Maven multi-module version of the project to familiarise with the microservices architecture.

**Pre-requisites:**
- JDK 17+
- Apache Maven
- MongoDB
- MySQL
- Keycloak (requires JDK 17+)
  > To run Keycloak locally: <br>
  > `cd PATH-TO\keycloak\` <br>
  > `.\bin\kc.bat start-dev` <br>
  >
  > Port can be chosen with: <br>
  > `.\bin\kc.bat start-dev --http-port=8181`, <br>
  > or adding `http-port=8181` to  `.\conf\keycloak.conf` 

- Zipkin (requires JDK 17+)
  >   Installation guide: https://zipkin.io/pages/quickstart <br>
  >
  > To prepare Zipkin locally: <br>
  > `git clone https://github.com/openzipkin/zipkin` <br>
  > `cd zipkin` <br> 
  > `./mvnw -T1C ~~-q --batch-mode -DskipTests --also-make -pl zipkin-server clean package` <br>
  >
  > To run Zipkin locally: <br>  
  > *(Normal server)* `java -jar ./zipkin-server/target/zipkin-server-3.3.1-SNAPSHOT-exec.jar` <br>
  > *(Slim server)* `java -jar ./zipkin-server/target/zipkin-server-*slim.jar`


## product-service
Service to request products.

- Spring Boot framework
- Apache Maven
- MongoDB
- Testcontainers framework
  > Used for automated integration tests.
  >
  > *Requires Docker - not currently working*
- Spring Cloud Netflix Eureka
  > Used to be discovered by `discovery-server`.

## order-service
Service to place orders of products.

- Spring Boot framework
- Apache Maven
- MySQL
- Spring Webflux
- Spring Cloud Netflix Eureka
- Resilience4J Circuit Breaker
  > Used to implement the Circuit Breaker pattern to interrupt requests to failed services.


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
  > Used to find instances of the client services.
- Spring Boot Security


## api-gateway
Service to route requests to the corresponding service.

- Apache Maven
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Keycloak 
  > Used for security authorisation