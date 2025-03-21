package com.skav.pokedex.converter;

import com.skav.pokedex.converter.interfaces.PokemonConverter;
import com.skav.pokedex.dto.PokemonDTO;
import com.skav.pokedex.service.interfaces.PokemonService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PokemonConverterTests {

    private static final Logger logger = LoggerFactory.getLogger(PokemonConverterTests.class);

    @Autowired
    PokemonConverter pokemonConverter;
    @Autowired
    private PokemonService pokemonService;

    @Test
    void testPokemonConverter() throws ExecutionException, InterruptedException {
        String pokemonName = "pikachu";
        logger.info("Get pokemon information for: {}", pokemonName);
        Map<String, Object> result = pokemonService.getPokemonSpecies(pokemonName);

        PokemonDTO pokemon = pokemonConverter.fromMapToPokemonDTO(result);
        assertNotNull(pokemon);
        assertEquals(pokemon.getDescription(), "When several of\nthese POKéMON\ngather, their\felectricity could\nbuild and cause\nlightning storms.");
        assertEquals(pokemon.getHabitat(), "forest");
        assertEquals(pokemon.getIsLegendary(), false);
    }
}
