package com.skav.pokedex.controller;

import com.skav.pokedex.controller.interfaces.PokemonController;
import com.skav.pokedex.dto.PokemonDTO;
import com.skav.pokedex.response.HttpResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PokemonControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(PokemonControllerTests.class);
    @Autowired
    PokemonController pokemonController;

    @Test
    void testGetPokemonByName() {
        /*End to end test for testing first endpoint */
        String pokemonName = "pikachu";
        HttpResponse<PokemonDTO> response = pokemonController.getPokemonByName(pokemonName);

        PokemonDTO expectedPokemon = new PokemonDTO();
        expectedPokemon.setName(pokemonName);
        expectedPokemon.setDescription("When several of\nthese POKéMON\ngather, their\felectricity could\nbuild and cause\nlightning storms.");
        expectedPokemon.setHabitat("forest");
        expectedPokemon.setIsLegendary(false);

        assertNotNull(response);
        assertEquals(response.getStatus(), "OK");
        assertEquals(response.getData(), expectedPokemon);
        assertEquals(response.getMessage(), null);
    }

    @Test
    void testTranslatedPokemonByName() {
        /*End to end test for testing second endpoint */
        String pokemonName = "pikachu";
        HttpResponse<PokemonDTO> response = pokemonController.getTranslatedPokemonByName(pokemonName);

        assertNotNull(response);
        assertEquals(response.getStatus(), "OK");
        assertEquals(response.getMessage(), null);
        assertEquals(response.getData().getName(), pokemonName);
        assertEquals(response.getData().getHabitat(), "forest");
        assertEquals(response.getData().getIsLegendary(), false);
    }

    @Test
    void testGetPokemonByNameNotFound() {
        /*End to end test for testing first endpoint with no existing pokemon*/
        String pokemonName = "POKEMON_NOT_FOUND";
        HttpResponse<PokemonDTO> response = pokemonController.getPokemonByName(pokemonName);

        assertNotNull(response);
        assertEquals(response.getStatus(), "KO");
        assertNotNull(response.getMessage());
        assertTrue(response.getMessage().startsWith("404"));
    }

    @Test
    void testTranslatedPokemonByNameNotFound() {
        /*End to end test for testing second endpoint with no existing pokemon*/
        String pokemonName = "POKEMON_NOT_FOUND";
        HttpResponse<PokemonDTO> response = pokemonController.getTranslatedPokemonByName(pokemonName);

        assertNotNull(response);
        assertEquals(response.getStatus(), "KO");
        assertNotNull(response.getMessage());
        assertTrue(response.getMessage().startsWith("404"));
    }

}
