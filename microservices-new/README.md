
# microservice-new
A Maven multi-module version of the project to familiarise with the microservices architecture.

**Pre-requisites:**
- JDK 17+
- Apache Maven
- MongoDB
- MySQL
- Keycloak (requires JDK 17+)
  > To run Keycloak locally:  
  > `cd PATH-TO\keycloak\`  
  > `.\bin\kc.bat start-dev`  
  
  > Port can be chosen with:  
  > `.\bin\kc.bat start-dev --http-port=8181`,  
  > or adding `http-port=8181` to  `.\conf\keycloak.conf`

- Zipkin (requires JDK 17+)
  > Installation guide: https://zipkin.io/pages/quickstart
  
  > To prepare Zipkin locally:  
  > `git clone https://github.com/openzipkin/zipkin`  
  > `cd zipkin`  
  > `./mvnw -T1C ~~-q --batch-mode -DskipTests --also-make -pl zipkin-server clean package`  
  
  > To run Zipkin locally:  
  > *(Normal server)* `java -jar ./zipkin-server/target/zipkin-server-3.3.1-SNAPSHOT-exec.jar`  
  > *(Slim server)* `java -jar ./zipkin-server/target/zipkin-server-*slim.jar`

- Zookeeper (to run Apache Kafka)
  > Installation guide: https://zookeeper.apache.org/doc/current/zookeeperStarted.html

  > To run Zookeeper locally:  
  > `cd apache-zookeper-3.9.2`   
  > `./bin/zkServer.sh start`  
  > `./bin/zkCli.sh -server 127.0.0.1:2181`  
  
  > By default, Zookeeper server uses port 8080.  
  > 
  > To change the server port, add `admin.serverPort=9876` to `./conf/zoo.cfg`

- Apache Kafka
  > Installation guide: https://kafka.apache.org/quickstart  
  > 
  > To run Apache Kafka (with Zookeeper):  
  > Run ***Zookeeper*** as above  
  > `cd kafka_2.13-3.7.0`  
  > `./bin/kafka-server-start.sh config/server.properties`

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
- Zipkin
  > Used for distributed tracing.

## order-service
Service to place orders of products.

- Spring Boot framework
- Apache Maven
- MySQL
- Spring Webflux
- Spring Cloud Netflix Eureka
- Resilience4J Circuit Breaker
  > Used to implement the Circuit Breaker pattern to interrupt requests to failed services.
- Zipkin
- Kafka
  > Used to send notifications when an order is placed to the *notificationTopic* Kafka topic.

## inventory-service
Service to check inventory of products.

- Spring Boot framework
- Apache Maven
- MySQL
- Spring Cloud Netflix Eureka
- Zipkin


## discovery-server
Service to find IP address of multiple instances of other services.

- Apache Maven
- Spring Cloud Netflix Eureka
  > Used to find instances of the client services.
- Spring Boot Security
- Zipkin


## api-gateway
Service to route requests to the corresponding service.

- Apache Maven
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Keycloak 
  > Used for security authorisation
- Zipkin

## notification-service
Service to receive notifications about orders.

- Spring Cloud Netflix Eureka
- Zipkin
- Kafka
  > Used to receive order notifications from the *notificationTopic* Kafka topic.