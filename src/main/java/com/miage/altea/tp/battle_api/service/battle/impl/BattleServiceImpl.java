package com.miage.altea.tp.battle_api.service.battle.impl;

import com.miage.altea.tp.battle_api.bo.battle.Battle;
import com.miage.altea.tp.battle_api.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle_api.bo.battle.BattlePokemonFactory;
import com.miage.altea.tp.battle_api.bo.battle.BattleTrainer;
import com.miage.altea.tp.battle_api.service.battle.BattleService;
import com.miage.altea.tp.battle_api.service.battle.StatsCalculator;
import com.miage.altea.tp.battle_api.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleServiceImpl implements BattleService {
    private TrainerService trainerService;

    private BattlePokemonFactory battlePokemonFactory;

    private StatsCalculator statsCalculator;

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

    @Override
    public Battle attack(String attackerName, Battle battle) throws Exception {
        if(!battle.getTrainer().getName().equals(attackerName) && !battle.getOpponent().getName().equals(attackerName)) throw new Exception("Trainer don't exists");
        var attacker = (battle.getTrainer().getName().equals(attackerName)) ? battle.getTrainer() : battle.getOpponent();
        var opponent = (battle.getTrainer().getName().equals(attackerName)) ? battle.getOpponent() : battle.getTrainer();
        if(!attacker.isNextTurn()) throw new Exception("Not your turn!");
        var attackerPokemonOpt = attacker.getTeam().stream().filter(BattlePokemon::isAlive).findFirst();
        var opponentPokemonOpt = opponent.getTeam().stream().filter(BattlePokemon::isAlive).findFirst();
        if(!attackerPokemonOpt.isPresent()  || !opponentPokemonOpt.isPresent()) throw new Exception("One trainer hasn't pokemon");
        var attackPokemon = attackerPokemonOpt.get();
        var opponentPokemon = opponentPokemonOpt.get();
        var newHpOpponent = opponentPokemon.getHp() - statsCalculator.calculateDamage(attackPokemon,opponentPokemon);
        newHpOpponent = (newHpOpponent <= 0) ? 0 : newHpOpponent;
        opponentPokemon.setHp(newHpOpponent);
        opponentPokemon.setKo(newHpOpponent == 0);
        opponentPokemon.setAlive(newHpOpponent > 0);
        opponent.setNextTurn(true);
        attacker.setNextTurn(false);
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

    @Autowired
    public void setStatsCalculator(StatsCalculator statsCalculator) {
        this.statsCalculator = statsCalculator;
    }
}
