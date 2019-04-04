package com.miage.altea.tp.battle_api.service.trainer;

import com.miage.altea.tp.battle_api.bo.trainer.Trainer;

public interface TrainerService {
    Trainer getTrainerByName(String name);
}
