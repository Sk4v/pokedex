package com.skav.pokedex.dto;

import lombok.Data;

@Data
public class PokemonDTO {
    String name;
    String description;
    String habitat;
    Boolean isLegendary;
}
