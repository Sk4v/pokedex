# Pokedex API
### Project Structure
- `pokedex-api`--> java project
- `k6` --> performance tests
- `docker-compose` --> includes the REST API + some monitoring services
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
The service has been designed with the intent to easily add more features over time.

Simulating that this project is a serious one that will be maintained in the long term and deployed in production at a client’s site, the following procedures and approach are described.

These are the following points
1. *Documentation, OpenAPI & SWAGGER*
2. *Monitoring*
3. *Application Tests (Java)*
3. *Performance tests*
4. *OWASP SCA Analysis*
5. *Automatic Coding Linting & refactoring*

### 1. OpenAPI & SWAGGER
The API provides two endpoints:
1. `http://localhost:8080/api-docs` which provides the file in the OpenAPI standard.
2. `http://localhost:8080/swagger-ui/index.html` which provides the Swagger UI interface that reads the OpenAPI.json file.

This approach allow me to (example):
- provides this documentation to other teams for build another service that dependens on it or for developing a UI
- allow me to test and verify easily my REST API 
- I can generate automatically differents client call (ex. using OpenAPI-generator)

### 2. Monitoring
### 3. Application Tests (Java)
### 3.1 Performance tests
### 4. OWASP SCA Analysis
### 5. Automatic Coding Linting & refactoring

