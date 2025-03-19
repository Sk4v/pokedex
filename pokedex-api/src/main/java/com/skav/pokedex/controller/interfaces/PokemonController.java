package com.skav.pokedex.controller.interfaces;

import com.skav.pokedex.dto.PokemonDTO;
import com.skav.pokedex.response.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pokemon")
public interface PokemonController {

    @GetMapping("/{name}")
    HttpResponse<PokemonDTO> getPokemonByName(@PathVariable String name);

    @GetMapping("/translated/{name}")
    HttpResponse<PokemonDTO> getTranslatedPokemonByName(@PathVariable String name);
}
