package com.skav.pokedex.service.interfaces;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface PokemonService {
    CompletableFuture<Map<String, Object>> getPokemonSpecies(String name);
}
