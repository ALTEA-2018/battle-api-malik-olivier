package com.miage.altea.tp.battle_api.service.trainer.impl;

import com.miage.altea.tp.battle_api.bo.trainer.Trainer;
import com.miage.altea.tp.battle_api.service.pokemon_type.PokemonTypeService;
import com.miage.altea.tp.battle_api.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String trainerServiceUrl;
    private PokemonTypeService pokemonTypeService;

    @Override
    public Trainer getTrainerByName(String name) {
        var trainer = restTemplate.getForObject(trainerServiceUrl+"/trainers/{name}",Trainer.class,name);
        trainer.getTeam().parallelStream().forEach(pokemon -> {
            pokemon.setType(pokemonTypeService.getPokemonType(pokemon.getPokemonType()));
        });
        return trainer;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainers.service.url}")
    public void setTrainerServiceUrl(String pokemonServiceUrl) {
        this.trainerServiceUrl = pokemonServiceUrl;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}
