package com.skav.pokedex.controller.impl;

import com.skav.pokedex.controller.interfaces.PokemonController;
import com.skav.pokedex.converter.interfaces.PokemonConverter;
import com.skav.pokedex.dto.PokemonDTO;
import com.skav.pokedex.response.HttpResponse;
import com.skav.pokedex.service.interfaces.PokemonService;
import com.skav.pokedex.service.interfaces.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class PokemonControllerImpl implements PokemonController {

    @Autowired
    TranslationService translationService;
    @Autowired
    PokemonConverter pokemonConverter;
    @Autowired
    PokemonService pokemonService;

    private static final Logger logger = LoggerFactory.getLogger(PokemonControllerImpl.class);

    @Override
    public HttpResponse<PokemonDTO> getPokemonByName(String name) {
        /*Given a Pokemon name, returns standard Pokemon description and additional information.*/
        logger.info("Get Pokemon information for: {}", name);
        CompletableFuture<Map<String, Object>> pokemonFuture = pokemonService.getPokemonSpecies(name);

        HttpResponse<PokemonDTO> response = new HttpResponse();
        try {
            Map<String, Object> pokemonDetails = pokemonFuture.get();
            PokemonDTO pokemonDTO = pokemonConverter.fromMapToPokemonDTO(pokemonDetails);
            response.setStatus("OK");
            response.setData(pokemonDTO);

        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error {}", e.getMessage());
            response.setStatus("KO");
            response.setMessage(e.getMessage());
        }

        logger.info("Response: {}", response);
        return response;
    }

    @Override
    public HttpResponse<PokemonDTO> getTranslatedPokemonByName(String name) {
        /*Given a Pokemon name, return translated Pokemon description and other basic information using
        the following rules:
        1. If the Pokemon’s habitat is cave or it’s a legendary Pokemon then apply the Yoda translation.
        2. For all other Pokemon, apply the Shakespeare translation.
        3. If you can’t translate the Pokemon’s description then use the standard description*/

        HttpResponse response = new HttpResponse();
        return response;
    }
}
