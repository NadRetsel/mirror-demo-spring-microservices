
# microservice-new
A Maven multi-module version of the project to familiarise with the microservices architecture.  

The demo project was created on a VDI without access to install Docker, so each service is run locally on a set port.  

There are issues running Apache Kafka locally with *Windows Command Prompt / Power Shell*, so it is recommended to use *Git Bash* instead. Zookeeper can be run separately with *Windows CP / PS*, or run the version bundled with Kafka using *Git Bash*.


**Pre-requisites:**  
- JDK 17+
- Apache Maven
- *Databases*
  - MongoDB
  - MySQL
- *Services*
  - Keycloak (requires JDK 17+)
    > **Installation guide**  
    https://www.keycloak.org/getting-started/getting-started-zip  

    > **Running Keycloak locally**  
    > - Go to the directory  
        `cd [...]/keycloak-24.0.2`  
    > - Run the service  
        `./bin/kc.bat start-dev`  

    > Port can be chosen with `.\bin\kc.bat start-dev --http-port=8181`,    
      or adding  `http-port=8181` to  `.\conf\keycloak.conf`
  
    > Check the service is running by going to http://localhost:8181
    > - Username = `root`
    > - Password = `root`

  - Zipkin (requires JDK 17+)
    > **Installation guide**  
      https://zipkin.io/pages/quickstart
    
    > **Preparing Zipkin:**  
    > - Clone from the Github  
        `git clone https://github.com/openzipkin/zipkin`  
    > - Go to the directory  
        `cd [...]/zipkin`  
    > - Set up the configurations  
        `./mvnw -T1C -q --batch-mode -DskipTests --also-make -pl zipkin-server clean package`  
  
    > **Running Zipkin locally** 
    > - Go to the directory  
        `cd [...]/zipkin`
    > - Start a normal server  
        `java -jar ./zipkin-server/target/zipkin-server-3.3.1-SNAPSHOT-exec.jar`  
        *(Note: `zipkin-server-*.jar` may be a different version)*  
    > - Or, start a slim server  
        `java -jar ./zipkin-server/target/zipkin-server-*slim.jar`
  
    > Check the service is running by going to http://localhost:9411

  - Zookeeper (to run Apache Kafka)
    > **Installation guide**  
      https://zookeeper.apache.org/doc/current/zookeeperStarted.html

    > **Running Zookeeper locally**
    > - Go to the directory  
        `cd [...]/apache-zookeper-3.9.2-bin`   
    > - Run the server  
        `./bin/zkServer.sh start`
    > - Connect to the server  
        `./bin/zkCli.sh -server 127.0.0.1:2181`
  
    > By default, Zookeeper server uses port `8080`.  
    > 
    > To change the server port, add `admin.serverPort=9876` to `./conf/zoo.cfg`
  
    - Apache Kafka
    > **Installation guide:**  
        https://kafka.apache.org/quickstart  

    > **Running Kafka locally with**
    > - Run Zookeeper as above, or run through Kafka (with *Git Bash*) with  
        `./bin/zookeeper-server-start.sh ./config/zookeeper.properties` 
    > - Open the *Git Bash* shell (will not work with *Windows Command Prompt / Power Shell*)
    > - Go to the directory  
          `cd [...]/kafka_2.13-3.7.0`
    > - Start the server  
          `./bin/kafka-server-start.sh ./config/server.properties`
    
    > ***IMPORTANT***  
      Kafka needs to be run with *Git Bash*, NOT *Windows Command Prompt / Power Shell*.  
    >   
    > If you really need to run with *Windows CP / PS*, the `kafka_*` directory MUST be in `C:\kafka_*` and run with `.\bin\windows\kafka-server-start.bat .\config\server.properties`.  
    > 
    > Trying to run the command while the directory is in `C:\...\ExampleDirectory\kafka_*` will result in a `The input line is too long.` error due to path name being too long.

  - Prometheus
    > **Installation guide**  
      https://prometheus.io/docs/prometheus/latest/getting_started/

    > **Running Prometheus locally**
    > - Go to the directory  
        `cd [...]/prometheus-2.51.2.windows-amd64`
    > - Run the service  
        `./prometheus --config.file='D:\Users\dsalvador\Documents\Personal Projects\Spring Microservices Demo\microservices-new\prometheus\prometheus.yml'`  
        *(Change path to the needed config `prometheus.yml` file)*
    
    > Check it is running by going to http://localhost:9090  

  - Grafana
    > **Installation guide**  
      https://grafana.com/docs/grafana/latest/setup-grafana/  
  
    > **Running Grafana locally**
    > - Run Prometheus as above 
    > - Go to the Grafana directory  
        `cd [...]/grafana-v10.4.2`
    > - Run the server
        `./bin/grafana server`
    
    > Check it is running by going to http://localhost:3000
    > - Username = `admin`
    > - Password = `admin`
  

## product-service
Service to request products.  
Runs on `localhost:8081`

- Spring Boot framework
- Apache Maven
- MongoDB
- Testcontainers framework
  > Used for automated integration tests.
  >
  > *Requires Docker - not currently working*
- Spring Cloud Netflix Eureka
  > Used to be discovered by `discovery-server`
- Zipkin
  > Used for distributed tracing.
- Prometheus
  > Used for monitoring (combined with `Grafana`).


## order-service
Service to place orders of products.  
Runs on `localhost:8082`

- Spring Boot framework
- Apache Maven
- MySQL
- Spring Webflux
  > Used in `WebClientConfig.java` for inter-service communication with `inventory-service`
- *Spring Cloud Netflix Eureka*
- Resilience4J Circuit Breaker
  > Used to implement the Circuit Breaker pattern to interrupt requests to failed services.
- *Zipkin*
- Kafka
  > Used to send notifications when an order is placed to the *notificationTopic* Kafka topic.
- *Prometheus*


## inventory-service
Service to check inventory of products.  
Runs on `localhost:8083`

- Spring Boot framework
- Apache Maven
- MySQL
- *Spring Cloud Netflix Eureka*
- *Zipkin*
- *Prometheus*

## discovery-server
Service to find IP address of multiple instances of other services.  
Runs on `localhost:8761`

- Apache Maven
- Spring Cloud Netflix Eureka
  > Used to find instances of the client services.
- Spring Boot Security
- *Zipkin*
- *Prometheus*


## api-gateway
Service to route requests to the corresponding service.  
Runs on `localhost:8080`

- Apache Maven
- *Spring Cloud Netflix Eureka*
- Spring Cloud Gateway
  > Used to create the gateway.
- Keycloak 
  > Used for security authorisation.
- *Zipkin*
- *Prometheus*


## notification-service
Service to receive notifications about orders.  
Runs on `localhost:8084`

- *Spring Cloud Netflix Eureka*
- *Zipkin*
- *Kafka*
  > Used to receive order notifications from the `notificationTopic` Kafka topic.
- *Prometheus*