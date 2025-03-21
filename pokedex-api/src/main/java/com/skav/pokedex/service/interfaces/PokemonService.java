package com.skav.pokedex.service.interfaces;

import java.util.Map;

public interface PokemonService {
    Map<String, Object> getPokemonSpecies(String name);
}
