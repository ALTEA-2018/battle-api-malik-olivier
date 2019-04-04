package com.miage.altea.tp.battle_api.bo.battle;

import com.miage.altea.tp.battle_api.bo.pokemon_type.PokemonType;
import com.miage.altea.tp.battle_api.service.battle.StatsCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattlePokemonFactory {

    private StatsCalculator statsCalculator;

    public BattlePokemon  createBattlePokemon(PokemonType pokemonType, int level) {
        var battlePokemon = new BattlePokemon(pokemonType,level);
        var hp = statsCalculator.calculateHp(pokemonType.getStats().getHp(),level);
        var attack = statsCalculator.calculateStat(pokemonType.getStats().getAttack(),level);
        var speed = statsCalculator.calculateStat(pokemonType.getStats().getSpeed(),level);
        var defense = statsCalculator.calculateStat(pokemonType.getStats().getDefense(),level);
        battlePokemon.setHp(hp);
        battlePokemon.setMaxHp(hp);
        battlePokemon.setAttack(attack);
        battlePokemon.setDefense(defense);
        battlePokemon.setSpeed(speed);
        battlePokemon.setAlive(true);
        battlePokemon.setKo(false);
        return battlePokemon;
    }

    @Autowired
    public void setStatsCalculator(StatsCalculator statsCalculator) {
        this.statsCalculator = statsCalculator;
    }
}
