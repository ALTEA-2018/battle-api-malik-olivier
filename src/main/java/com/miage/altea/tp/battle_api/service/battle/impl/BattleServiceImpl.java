package com.miage.altea.tp.battle_api.service.battle.impl;

import com.miage.altea.tp.battle_api.bo.battle.Battle;
import com.miage.altea.tp.battle_api.bo.battle.BattlePokemonFactory;
import com.miage.altea.tp.battle_api.bo.battle.BattleTrainer;
import com.miage.altea.tp.battle_api.service.battle.BattleService;
import com.miage.altea.tp.battle_api.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleServiceImpl implements BattleService {
    private TrainerService trainerService;

    private BattlePokemonFactory battlePokemonFactory;


    @Override
    public Battle createBattle(String attackerName, String opponentName) {
        var battle = new Battle();
        var attacker = trainerService.getTrainerByName(attackerName);
        var opponent= trainerService.getTrainerByName(opponentName);
        var pokedexAttacker = attacker.getTeam().stream().map(
                pokemon -> battlePokemonFactory.createBattlePokemon(pokemon.getType(),pokemon.getLevel())
        ).collect(Collectors.toList());

        var pokedexOpponent = opponent.getTeam().stream().map(
                pokemon -> battlePokemonFactory.createBattlePokemon(pokemon.getType(),pokemon.getLevel())
        ).collect(Collectors.toList());
        battle.setTrainer(new BattleTrainer(attacker.getName(),true,pokedexAttacker));
        battle.setOpponent(new BattleTrainer(opponent.getName(),false,pokedexOpponent));
        battle.setUuid(UUID.randomUUID());
        return battle;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setBattlePokemonFactory(BattlePokemonFactory battlePokemonFactory) {
        this.battlePokemonFactory = battlePokemonFactory;
    }
}
