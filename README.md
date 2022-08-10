# Process user task orchestration: Hiring

## Description

This Quickstart showcases a basic implementation of the **Coffee Shop** process. 


The required *Kogito and Infrastructure Services* for this example are:

- Infinispan / Postgresql
- Kafka
- Kogito Data Index
- Kogito Management Console
- Kogito Task Console
- Keycloak server

## Running the Quickstart

### Prerequisites

* Java 11+ installed
* Environment variable JAVA_HOME set accordingly
* Maven 3.8.1+ installed
* Docker and Docker Compose to run the required example infrastructure.

And when using native image compilation, you will also need: 
  - GraalVM 20.3+ installed
  - Environment variable GRAALVM_HOME set accordingly
  - GraalVM native image needs as well native-image extension: https://www.graalvm.org/reference-manual/native-image/
  - Note that GraalVM native image compilation typically requires other packages (glibc-devel, zlib-devel and gcc) to be installed too, please refer to GraalVM installation documentation for more details.

### Starting the Kogito and Infrastructure Services

This quickstart provides a docker compose template that starts all the required services. This setup ensures that all services are connected with a default configuration.

<p align="center"><img width=75% height=50% src="docs/images/services.png"></p>

### Run Example with PostgreSQL

#### Compile Hiring example with profile postgresql

First thing is to compile the example with the postgresql profile executing:

For Linux and MacOS:

- Open a Terminal
- Go to the example folder and run
```sh
mvn clean install -Ppostgresql
```

#### Start infrastructure services

You should start all the services before you execute any of the **Hiring** example, to do that please execute:

For Linux and MacOS:

1. Open a Terminal
2. Go to docker-compose folder
3. Run the ```startServices.sh``` script

```bash
sh ./startServices.sh
```

or

```bash
sh ./startServices.sh postgresql
```

Once all services bootstrap, the following ports will be assigned on your local machine:

- PostgreSQL: 5432
- Kafka: 9092
- Data Index: 8180
- Management Console: 8280
- Task Console: 8380
- Keycloak: 8480
- PgAdmin: 8055

> **_NOTE:_**  This step requires the project to be compiled, please consider running a ```mvn clean install``` command on the project root before running the ```startServices.sh``` script for the first time or any time you modify the project.

Once started you can simply stop all services by executing the ```docker-compose -f docker-compose-postgresql.yml stop```.

All created containers can be removed by executing the ```docker-compose -f docker-compose-postgresql.yml rm```.

#### Run the Hiring example with PostgreSQL

##### Compile and Run Hiring example process in Local Dev Mode

Once all the infrastructure services are ready, you can start the Hiring example by doing:

- Open a Terminal
- Go to the hiring example folder
- Start the example with the command

```bash
mvn clean package quarkus:dev -Ppostgresql
```

NOTE: With dev mode of Quarkus you can take advantage of hot reload for business assets like processes, rules, decision tables and java code. No need to redeploy or restart your running application.

##### Package and Run in JVM mode

```sh
mvn clean package -Ppostgresql
java -jar target/quarkus-app/quarkus-run.jar
```

or on windows

```sh
mvn clean package -Ppostgresql
java -jar target\quarkus-app\quarkus-run.jar
```

##### Package and Run using Local Native Image
Note that this requires GRAALVM_HOME to point to a valid GraalVM installation

```sh
mvn clean package -Pnative -Ppostgresql
```

To run the generated native executable, generated in `target/`, execute

```sh
./target/./target/process-usertasks-quarkus-with-console-runner
```

### Run Example with Infinispan

#### Compile Hiring example with profile infinispan

First thing is to compile the example with the infinispan profile executing:

For Linux and MacOS:

1. Open a Terminal
2. Go to the example folder and run
```sh
mvn clean install -Pinfinispan
```
#### Start infrastructure services

You should start all the services before you execute any of the **Hiring** example, to do that please execute:

For Linux and MacOS:

1. Open a Terminal
2. Go to docker-compose folder
3. Run the ```startServices.sh``` script with infinispan argument

```bash
sh ./startServices.sh infinispan
```

Once all services bootstrap, the following ports will be assigned on your local machine:

- Infinispan: 11222
- Kafka: 9092
- Data Index: 8180
- Management Console: 8280
- Task Console: 8380
- Keycloak: 8480

> **_NOTE:_**  This step requires the project to be compiled, please consider running a ```mvn clean install -Pinfinispan``` command on the project root before running the ```startServices.sh infinispan``` script for the first time or any time you modify the project.

Once started you can simply stop all services by executing the ```docker-compose -f docker-compose-infinispan.yml stop```.

All created containers can be removed by executing the ```docker-compose -f docker-compose-infinispan.yml rm```.

#### Run the Hiring example with Infinispan

##### Compile and Run Hiring example process in Local Dev Mode

Once all the infrastructure services are ready, you can start the Hiring example by doing:

- Open a Terminal
- Go to the hiring example folder
- Start the example with the command

```bash
mvn clean package quarkus:dev -Pinfinispan
```

NOTE: With dev mode of Quarkus you can take advantage of hot reload for business assets like processes, rules, decision tables and java code. No need to redeploy or restart your running application.

##### Package and Run in JVM mode

```sh
mvn clean package -Pinfinispan
java -jar target/quarkus-app/quarkus-run.jar
```

or on windows

```sh
mvn clean package -Pinfinispan
java -jar target\quarkus-app\quarkus-run.jar
```

##### Package and Run using Local Native Image
Note that this requires GRAALVM_HOME to point to a valid GraalVM installation

```sh
mvn clean package -Pnative -Pinfinispan
```

To run the generated native executable, generated in `target/`, execute

```sh
./target/./target/process-usertasks-quarkus-with-console-runner
```

### Using Keycloak as Authentication Server

In this Quickstart we'll be using [Keycloak](https://www.keycloak.org/) as *Authentication Server*. It will be started as a part of the project *Infrastructure Services*, you can check the configuration on the project [docker-compose.yml](docker-compose/docker-compose.yml) in [docker-compose](docker-compose) folder.

It will install the *Kogito Realm* that comes with a predefined set of users:
| Login         | Password   | Roles               |
| ------------- | ---------- | ------------------- |
|    admin      |   admin    | *admin*, *managers* |
|    alice      |   alice    | *user*              |
|    jdoe       |   jdoe     | *managers*          |

Once Keycloak is started, you should be able to access your *Keycloak Server* at [localhost:8480/auth](http://localhost:8480/auth) with *admin* user.

### Submit a request to start new hiring

Once the service is up and running you can make use of the **Hiring** application by a sending request to `http://localhost:8080/coffee-shop`  with following content:
```json
{   
    "candidate": {
        "name": "Jon Snow",
        "email": "jsnow@example.com",
        "salary": 30000,
        "skills": "Java, Kogito"
    }
}
```

In a Terminal you can execute this command to start a **Hiring** process for the "Jon Snow" candidate:
```bash
curl -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://localhost:8080/hiring -d @- << EOF
{   
    "candidate": {
        "name": "Jon Snow",
        "email": "jsnow@example.com",
        "salary": 30000,
        "skills": "Java, Kogito"
    }
}
EOF
```

### Access Kogito Management Console

To access the Kogito Management Console just open your browser and navigate to ``http://localhost:8280``. You'll be redirected to the *Keycloak* log in page.


> **_NOTE:_**  For more information about how to work with Kogito Management Console, please refer to the [Kogito Documentation](https://docs.jboss.org/kogito/release/latest/html_single/#con-management-console_kogito-developing-process-services) page.


### Access Kogito Task Console

To access the Kogito Task Console just open your browser and navigate to ``http://localhost:8380``. You'll be redirected to the *Keycloak* log in page.

Once there, log in with an *managers* user (for example *jdoe*) and you should be redirected to the user **Task Inbox**:


