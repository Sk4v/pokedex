package com.skav.pokedex.service.impl;

import com.skav.pokedex.service.interfaces.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class PokemonServiceImpl extends AbstractService implements PokemonService {

    private static final Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);

    @Value("${pokeapi.base.url}")
    private String pokeApiBaseUrl;

    @Value("${pokeapi.pokemon.species}")
    private String pokemonSpecies;

    @Override
    public Map<String, Object> getPokemonSpecies(String name) {
        URI uri = URI.create(String.format("%s/%s/%s", pokeApiBaseUrl, pokemonSpecies, name));
        return mapResponseFromGetRequest(uri);
    }
}
