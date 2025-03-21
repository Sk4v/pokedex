package com.skav.pokedex.integration;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skav.pokedex.service.interfaces.PokemonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PokemonControllerIntegrationTest {

    /*This class provides integration tests of our system
    * To simulate the fully working impl I used mock */

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PokemonService pokemonService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Map<String, Object> generateMap(String path) throws IOException, StreamReadException, DatabindException {
        InputStream expectedJsonStream = new ClassPathResource(path).getInputStream();
        Map<String, Object> pokemonDetails = objectMapper.readValue(expectedJsonStream, Map.class);
        return pokemonDetails;
    }

    @Test
    public void testIntegrationGetPokemonByName() throws Exception, IOException, StreamReadException, DatabindException {
        String pokemonName = "pikachu";

        Map<String, Object> pokemonDetails = generateMap("e2e/pikachu.json");

        when(pokemonService.getPokemonSpecies(pokemonName)).thenReturn(pokemonDetails);

        mockMvc.perform(get("/pokemon/{name}", pokemonName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data.name").value(pokemonName))
                .andExpect(jsonPath("$.data.description").value("When several of\n" +
                        "these POKéMON\n" +
                        "gather, their\felectricity could\n" +
                        "build and cause\n" +
                        "lightning storms."));
    }

    @Test
    public void testIntegrationGetPokemonByNameNotFound() throws Exception, IOException, StreamReadException, DatabindException {
        String pokemonName = "POKEMON_NOT_FOUND";

        when(pokemonService.getPokemonSpecies(pokemonName)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found"));

        mockMvc.perform(get("/pokemon/{name}", pokemonName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("KO"))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    public void testIntegrationGetTranslatedPokemon(){
        //TODO
    }

    @Test
    public void testIntegrationGetTranslatedError(){
       //TODO
    }
}
