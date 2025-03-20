package com.skav.pokedex.controller;

import com.skav.pokedex.controller.interfaces.PokemonController;
import com.skav.pokedex.dto.PokemonDTO;
import com.skav.pokedex.response.HttpResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PokemonControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(PokemonControllerTests.class);
    @Autowired
    PokemonController pokemonController;

    @Test
    void testGetPokemonByName(){
        String pokemonName = "pikachu";
        HttpResponse<PokemonDTO> response = pokemonController.getPokemonByName(pokemonName);

        PokemonDTO expectedPokemon = new PokemonDTO();
        expectedPokemon.setName(pokemonName);
        expectedPokemon.setDescription("When several of\nthese POKéMON\ngather, their\felectricity could\nbuild and cause\nlightning storms.");
        expectedPokemon.setHabitat("forest");
        expectedPokemon.setIsLegendary(false);

        assertNotNull(response);
        assertEquals(response.getStatus(),"OK");
        assertEquals(response.getData(), expectedPokemon);
        assertEquals(response.getMessage(), null);
    }

    @Test
    void testTranslatedPokemonByName(){
    }

    @Test
    void testGetPokemonByNameNotFound(){
    }

    @Test
    void testTranslatedPokemonByNameNotFound(){
    }

}
