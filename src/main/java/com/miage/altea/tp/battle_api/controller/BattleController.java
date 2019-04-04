package com.miage.altea.tp.battle_api.controller;

import com.miage.altea.tp.battle_api.bo.battle.Battle;
import com.miage.altea.tp.battle_api.service.battle.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/battles")
public class BattleController {
    private BattleService battleService;

    private Map<UUID,Battle> battles = new HashMap();

    @PostMapping
    public Battle createBattle(@RequestParam("trainer") String attackerName, @RequestParam("opponent") String opponentName) {
        var battle = battleService.createBattle(attackerName,opponentName);
        battles.put(battle.getUuid(),battle);
        return  battle;
    }

    @GetMapping("/{uuidBattle}")
    public Battle getBattle(@PathVariable("uuidBattle") UUID uuidBattle) throws Exception {
        if(!battles.containsKey(uuidBattle)) throw new Exception("Battle don't exists");
        return  battles.get(uuidBattle);
    }

    @PostMapping("/{uuidBattle}/{attacker}/attack")
    public ResponseEntity<?> attack(@PathVariable("uuidBattle") UUID uuidBattle,@PathVariable("attacker") String attacker) {
        if(!battles.containsKey(uuidBattle)) return new ResponseEntity<>("Battle don't exists",HttpStatus.BAD_REQUEST);
        try {
            var battle = battleService.attack(attacker,battles.get(uuidBattle));
            battles.replace(uuidBattle,battle);
            return new ResponseEntity<>(battle,HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    public void setBattleService(BattleService battleService) {
        this.battleService = battleService;
    }
}
