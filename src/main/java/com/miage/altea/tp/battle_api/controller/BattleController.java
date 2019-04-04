package com.miage.altea.tp.battle_api.controller;

import com.miage.altea.tp.battle_api.bo.battle.Battle;
import com.miage.altea.tp.battle_api.service.battle.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/battles")
public class BattleController {
    private BattleService battleService;

    private HashMap<UUID,Battle> battles = new HashMap();

    @PostMapping
    public Battle createBattle(@RequestParam("trainer") String attackerName, @RequestParam("opponent") String opponentName) {
        var battle = battleService.createBattle(attackerName,opponentName);
        battles.put(battle.getUuid(),battle);
        return  battle;
    }

//    @GetMapping
//    public Battle getBattle(@PathParam())

    @Autowired
    public void setBattleService(BattleService battleService) {
        this.battleService = battleService;
    }
}
