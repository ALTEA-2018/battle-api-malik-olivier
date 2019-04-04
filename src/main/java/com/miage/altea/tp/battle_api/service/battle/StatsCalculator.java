package com.miage.altea.tp.battle_api.service.battle;

import com.miage.altea.tp.battle_api.bo.battle.BattlePokemon;

public interface StatsCalculator {
    int calculateHp(int base,int level);
    int calculateStat(int base,int level);
    int calculateDamage(BattlePokemon attack, BattlePokemon receive);
}
