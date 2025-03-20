package com.skav.pokedex.converter.interfaces;

import com.skav.pokedex.dto.PokemonDTO;

import java.util.Map;

public interface PokemonConverter {

    PokemonDTO fromMapToPokemonDTO(Map<String, Object> map);
}
