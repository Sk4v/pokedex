package com.skav.pokedex.controller.impl;

import com.skav.pokedex.controller.interfaces.PokemonController;
import com.skav.pokedex.dto.PokemonDTO;
import com.skav.pokedex.response.HttpResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonControllerImpl implements PokemonController {

    @Override
    public HttpResponse<PokemonDTO> getPokemonByName(String name) {
        /*Given a Pokemon name, returns standard Pokemon description and additional information.*/
        HttpResponse response = new HttpResponse();
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
