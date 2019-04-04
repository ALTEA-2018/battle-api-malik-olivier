package com.miage.altea.tp.battle_api.service.battle;

import com.miage.altea.tp.battle_api.bo.battle.Battle;
import com.miage.altea.tp.battle_api.bo.trainer.Trainer;

public interface BattleService {
    Battle createBattle(String attackerName, String opponentName);
}
