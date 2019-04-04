package com.miage.altea.tp.battle_api.service.pokemon_type.impl;

import com.miage.altea.tp.battle_api.bo.pokemon_type.PokemonType;
import com.miage.altea.tp.battle_api.service.pokemon_type.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {
    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    @Autowired
    @Override
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return restTemplate.getForObject(pokemonServiceUrl+"/pokemon-types/{id}",PokemonType.class,id);
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
