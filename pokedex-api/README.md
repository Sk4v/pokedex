!!! This is a simulated `README.md` following the arc42 template for the Pokedex project.

# Pokedex Project

## 1. Introduction and Goals

The Pokedex project is an API designed to retrieve and display information about Pokémon, including their descriptions, habitats, and other relevant details. It also integrates with external translation services to offer Pokémon descriptions in different languages, such as Yoda and Shakespeare styles, based on specific conditions.

### Key Goals:
- Retrieve detailed information about Pokémon, including descriptions, from **PokeAPI**.
- Translate Pokémon descriptions using **Funtranslations API**.
- Apply specific translation logic: Pokémon from the "Cave" habitat or legendary Pokémon will have their descriptions translated into Yoda style, while others will be translated into Shakespearean English.
- Provide a clean, RESTful API for users to interact with the Pokedex data.

## 2. Context and Scope

The Pokedex is a backend system built with **Java 17** and **Spring 3**. It interacts with two external APIs:
- **PokeAPI** to fetch detailed data about Pokémon.
- **Funtranslations API** to handle translation requests (Yoda and Shakespeare styles).

The system will expose a set of RESTful endpoints to retrieve Pokémon information, with support for translation as required.
## 3. Solution Strategy

### 3.1 Architecture Overview

- **PokemonService**: Interacts with the PokeAPI to fetch Pokémon data.
- **TranslationService**: Handles communication with Funtranslations API to translate Pokémon descriptions.
- **PokemonController**: Exposes RESTful endpoints for users to query Pokémon data and get translated descriptions.

### 3.2 Key Components

1. **PokemonService**: This service is responsible for fetching Pokémon data from PokeAPI.
2. **TranslationService**: Communicates with the Funtranslations API to provide translations based on the habitat or legendary status of a Pokémon.
3. **PokemonController**: Handles incoming HTTP requests and coordinates calls to the services.

### 3.3 Technology Stack
- **Java 17**: The backend language for the project.
- **Spring 3**: The framework used for building the REST API.
  
## 4. Architecture Decisions

- **Separation of Concerns**: The Pokémon-related logic is isolated in the `PokemonService`, while the translation logic is managed by the `TranslationService`.

## 5. System Requirements

- **Java 17** (JDK)
- **Maven** (for dependency management)
- **Docker** (for running external services locally, such as the Funtranslations API mock)

## 6. External Interfaces

### 6.1 PokeAPI

- **Base URL**: `https://pokeapi.co/api/v2/`
- **Endpoint**: `/pokemon-species/{pokemonName}` to retrieve Pokémon species information.

### 6.2 Funtranslations API

- **Base URL**: `https://funtranslations.com/api/`
- **Endpoints**:
  - `/yoda` for Yoda translation.
  - `/shakespeare` for Shakespeare translation.

### 6.3 API Endpoints

- **GET /pokemon/{name}**
  - Retrieves the basic Pokémon information (name, description, habitat, legendary status).
  
- **GET /pokemon/translated/{name}**
  - Retrieves the Pokémon information and the translated description (either Yoda or Shakespeare).

## 7. Quality Requirements

### 7.1 Performance

- I use K6 to tests my performance. 

### 7.2 Security

- Monitor the system with a continuos inspect of CVE using dependecy-check plugin.

## 8. Deployment

### 8.1 Local Setup

To run the application locally:

1. Clone the repository.
2. Run `mvn clean install` to build the project.
3. Start the Spring Boot application with `mvn spring-boot:run`.

### 8.2 Docker Setup

To run the application using Docker:

1. Build the Docker image:  
   `docker build -t pokedex .`
2. Run the application:  
   `docker run -p 8080:8080 pokedex`
