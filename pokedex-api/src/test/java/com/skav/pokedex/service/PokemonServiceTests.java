package com.skav.pokedex.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skav.pokedex.service.interfaces.PokemonService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAsync
public class PokemonServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(PokemonServiceTests.class);

    @Autowired
    private PokemonService pokemonService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    //TODO add tests descriptions

    @Test
    void testGetPokemonSpecies() throws ExecutionException, InterruptedException {
        String pokemonName = "pikachu";
        logger.info("Get pokemon information for: {}", pokemonName);
        Map<String, Object> result = pokemonService.getPokemonSpecies(pokemonName).get();

        assertNotNull(result);
        Map<String, Object> color = (Map<String, Object>) result.get("color");
        assertEquals(color.get("name"), "yellow");

        assertEquals(result.get("is_legendary"), false);

        Map<String, Object> habitat = (Map<String, Object>) result.get("habitat");
        assertNotNull(habitat);
        assertEquals(habitat.get("name"), "forest");

        //FIXME add assertions
    }

    @Test
    void testGetPokemonSpeciesE2e() throws IOException, ExecutionException, InterruptedException {
        String pokemonName = "pikachu";
        Map<String, Object> actualResult = pokemonService.getPokemonSpecies(pokemonName).get();
        assertNotNull(actualResult);

        InputStream expectedJsonStream = new ClassPathResource("e2e/pikachu.json").getInputStream();
        JsonNode actual = objectMapper.valueToTree(actualResult);
        JsonNode expected = objectMapper.readTree(expectedJsonStream);

        logger.info("Expected: {}", expected);
        logger.info("Actual: {}", actual);

        assertEquals(expected, actual);
    }

    @Test
    void testGetPokemonSpeciesNotFound() {
        String pokemonName = "POKEMON_NOT_FOUND";

        ExecutionException exception = assertThrows(ExecutionException.class, () -> {
            pokemonService.getPokemonSpecies(pokemonName).get();
        });
        assertTrue(exception.getCause() instanceof HttpClientErrorException.NotFound);
        HttpClientErrorException.NotFound notFoundException = (HttpClientErrorException.NotFound) exception.getCause();
        assertTrue(notFoundException.getMessage().startsWith("404 Not Found"));

        logger.info("Expected exception: {}", notFoundException.getMessage());
    }
}
