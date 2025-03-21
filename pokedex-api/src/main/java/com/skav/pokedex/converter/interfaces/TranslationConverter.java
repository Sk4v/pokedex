package com.skav.pokedex.converter.interfaces;

import com.skav.pokedex.dto.PokemonDTO;

import java.util.Map;

public interface TranslationConverter {

    String fromMapToString(Map<String, Object> map);
}
