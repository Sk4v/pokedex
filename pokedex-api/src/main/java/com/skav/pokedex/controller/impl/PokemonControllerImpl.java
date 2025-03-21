package com.skav.pokedex.controller.impl;

import com.skav.pokedex.controller.interfaces.PokemonController;
import com.skav.pokedex.converter.interfaces.PokemonConverter;
import com.skav.pokedex.converter.interfaces.TranslationConverter;
import com.skav.pokedex.dto.PokemonDTO;
import com.skav.pokedex.response.HttpResponse;
import com.skav.pokedex.service.interfaces.PokemonService;
import com.skav.pokedex.service.interfaces.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

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
    @Autowired
    TranslationConverter translationConverter;

    private static final Logger logger = LoggerFactory.getLogger(PokemonControllerImpl.class);

    @Override
    public HttpResponse<PokemonDTO> getPokemonByName(String name) {
        /*Given a Pokemon name, returns standard Pokemon description and additional information.*/
        logger.info("Get Pokemon information for: {}", name);

        HttpResponse<PokemonDTO> response = new HttpResponse();
        try {
            Map<String, Object> pokemonDetails = pokemonService.getPokemonSpecies(name);
            PokemonDTO pokemonDTO = pokemonConverter.fromMapToPokemonDTO(pokemonDetails);

            response.setStatus("OK");
            response.setData(pokemonDTO);
        } catch (HttpClientErrorException e) {
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
        logger.info("Get Pokemon information and traslation for: {}", name);

        HttpResponse<PokemonDTO> response = new HttpResponse();
        PokemonDTO pokemonDTO = null;
        Map<String, Object> translationMap = null;

        //Retrieve pokemon information
        try {
            Map<String, Object> pokemonDetails = pokemonService.getPokemonSpecies(name);
            pokemonDTO = pokemonConverter.fromMapToPokemonDTO(pokemonDetails);
        }catch (HttpClientErrorException e) {
            logger.error("Error {}", e.getMessage());
            response.setStatus("KO");
            response.setMessage(e.getMessage());
            logger.info("Response: {}", response);
            return response;
        }

        //Try to translate pokemon description
        try{
            if (pokemonDTO.getHabitat().equalsIgnoreCase("cave") || pokemonDTO.getIsLegendary()) {
                logger.info("Translate Pokemon description with YODA translation");
                translationMap = translationService.yodaTranslate(pokemonDTO.getDescription());
            } else {
                logger.info("Translate Pokemon description with SHAKESPEARE translation");
                translationMap = translationService.shakespeareTranslate(pokemonDTO.getDescription());
            }
        }catch (HttpClientErrorException e){
            logger.error("Error {}", e.getMessage());
            response.setMessage(e.getMessage());
        }

        //Change the default translation with the transleted one
        if(translationMap != null){
            String translatedDescription = translationConverter.fromMapToString(translationMap);
            pokemonDTO.setDescription(translatedDescription);
        }

        response.setStatus("OK");
        response.setData(pokemonDTO);
        logger.info("Response: {}", response);

        return response;
    }
}
