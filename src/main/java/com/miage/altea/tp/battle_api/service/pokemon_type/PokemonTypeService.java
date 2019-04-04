package com.miage.altea.tp.battle_api.service.pokemon_type;

import com.miage.altea.tp.battle_api.bo.pokemon_type.PokemonType;
import org.springframework.web.client.RestTemplate;


public interface PokemonTypeService {
    void setRestTemplate(RestTemplate restTemplate);
    PokemonType getPokemonType(int id);
}
