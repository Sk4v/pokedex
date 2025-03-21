# Pokedex API
### Project Structure
- `pokedex-api`--> java project
- `k6` --> performance tests
- `docker-compose` --> includes the REST API + prometheus
## Requirements and Installations
The `REST API` service is built with Java 17 and SpringBoot 3. 
Therefore, to run it on your PC, you will need to have the specified version of JAVA and MAVEN installed.
Inside the repository, there is a `.tool-versions` from [ASDF - Multiple Runtime Version Manager](https://asdf-vm.com/) which defines the required versions of dependencies like JAVA and Maven.

The service can also be run using `Docker`, so you can use the `docker-compose.yml` file to run the REST API along with other monitoring services:
```bin/bash
docker compose up -d
```
The project also contains a `Dockerfile` inside the pokedex-api dir.

## Approach
Simulating that this project is a serious one that will be maintained in the long term and deployed in production at a client’s site, the following procedures and approach are described.

These are the following points
1. *Documentation, OpenAPI & SWAGGER*
2. *Monitoring*
3. *Application Tests (Java)*
3. *Performance tests*
4. *OWASP SCA Analysis*
5. *Automatic Coding Linting & refactoring*

### 1. Documentation, OpenAPI & SWAGGER
#### `Documentation`
Into the `pokedex-api dir.` there is a README.md with a sample of `documentation`. It is based on `ARC-42 template` and allow me to describe very importants details like my architectural decision and the context of my system. 

#### `OpenAPI & SWAGGER`
The API provides two endpoints:
1. `http://localhost:8080/api-docs` which provides the file in the OpenAPI standard.
2. `http://localhost:8080/swagger-ui/index.html` which provides the Swagger UI interface that reads the OpenAPI.json file.

This approach allow me to (example):
- provides this documentation to other teams for build another service that dependens on it or for developing a UI
- allow me to test and verify easily my REST API 
- I can generate automatically differents client call (ex. using OpenAPI-generator)

### 2. Monitoring
Spring Boot allows me to use `actuator`, which provides access to essential application metrics. In this case, I have chosen to include the `micrometer-registry-prometheus` dependency to expose my metrics in the Prometheus format.
In the `docker-compose.yml` file, I have included the `Prometheus` service (`running on port :9090`), which allows me to collect the metrics exposed by the `actuator`. 
We can also import these metrics into a Grafana dashboard and monitor the service over time (I didn't add `Grafana` to the Docker Compose file for an easier and lightweight configuration)

### 3. Application Tests (Java)
I decided to test my system in different ways.
I created end-to-end (E2E) tests for my services and REST controllers.
Additionally, I implemented integration tests to verify the behavior of my services.

Thanks to the `jacoco` plugin, we can monitor our test coverage. We can find different kinds of reports in the `pokedex-api/target/site/jacoco` directory. An example file is `index.html` that i copied under `pokedex-api dir`. 

### 3.1 Performance tests
I used `grafana-k6` for performance testing.  
As an example, I created a very basic performance test.  
`K6` allows me to define different types of performance tests, and I can easily integrate it into a `CI/CD` pipeline.
Inside the k6 dir there is a `run-k6-sh` script that run the tests defined into the file `performance_tests.js` using docker. 
Use the following commands to run the script: 
Inside the `k6` directory, there is a `run-k6.sh` script that runs the tests defined in the `performance_tests.js` file using Docker.  
Use the following commands to run the script:
```/bin/bash
chmod +x run-k6.sh
./run-k6.sh
```

### 4. OWASP SCA Analysis  
I used the `dependency-check` Maven plugin for SCA (Software Composition Analysis). This allows me to detect common vulnerabilities in dependencies.  

Inside the `pokedex-api` directory, running the following command:  
```bash
mvn verify -Psecurity
```
The plugin generates the `dependency-check-report.html` file under the `target` directory, however there is a file that i copied into `pokedex-api dir`.

### 5. Automatic Coding Linting & refactoring
I added the `openrewrite` Maven plugin to perform optimizations such as Java linting and import optimization. Additionally, it can automatically refactor the codebase by applying best practices, improving maintainability, and ensuring consistency across the project.

Inside the `pokedex-api` directory, running the following command:  
```bash
mvn verify -Popenrewrite
```
You can see an example on commit `refactoring + minor fix`

### Observations
The steps described above are intended to be easy to insert into `CI/CD pipelines` so that you can constantly monitor the evolution of the project over time.


