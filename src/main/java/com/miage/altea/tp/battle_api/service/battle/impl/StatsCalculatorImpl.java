package com.miage.altea.tp.battle_api.service.battle.impl;

import com.miage.altea.tp.battle_api.bo.battle.BattlePokemon;
import com.miage.altea.tp.battle_api.service.battle.StatsCalculator;
import org.springframework.stereotype.Service;

@Service
public class StatsCalculatorImpl implements StatsCalculator {
    @Override
    public int calculateHp(int base, int level) {
        return 10 + level + (Math.round(base* level/50));
    }

    @Override
    public int calculateStat(int base, int level) {
        return 5 + (base* Math.round(level/50));
    }

    @Override
    public int calculateDamage(BattlePokemon attack, BattlePokemon receive) {
        var statsAttack = calculateStat(attack.getType().getStats().getAttack(),attack.getLevel());
        var statDefense = calculateStat(receive.getType().getStats().getDefense(),receive.getLevel());
        return ((Math.round(2*attack.getLevel())/5) + (2 * Math.round(statsAttack/statDefense))) +2 ;
    }
}
