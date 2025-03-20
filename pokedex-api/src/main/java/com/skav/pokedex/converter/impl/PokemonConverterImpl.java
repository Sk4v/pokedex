package com.skav.pokedex.converter.impl;

import com.skav.pokedex.converter.interfaces.PokemonConverter;
import com.skav.pokedex.dto.PokemonDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PokemonConverterImpl implements PokemonConverter {

    @Override
    public PokemonDTO fromMapToPokemonDTO(Map<String, Object> map) {

        PokemonDTO pokemon = new PokemonDTO();

        //Retrieve description
        String description;
        List<Map<String, Object>> flavorEntries = (List) map.get("flavor_text_entries");
        Map<String, Object> flavor = flavorEntries.stream().findFirst().orElse(null);
        if(flavor != null) description = (String) flavor.get("flavor_text");
        else description = null;

        //Retrieve habitat
        Map<String, Object> habitatMap = (Map<String, Object>) map.get("habitat");

        pokemon.setName((String) map.get("name"));
        pokemon.setDescription(description);
        pokemon.setHabitat((String) habitatMap.get("name"));
        pokemon.setIsLegendary((Boolean) map.get("is_legendary"));
        return pokemon;
    }
}
