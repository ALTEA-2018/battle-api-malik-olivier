package com.miage.altea.tp.battle_api.bo.battle;

import java.util.List;

public class BattleTrainer {
    private String name;
    private boolean nextTurn;
    private List<BattlePokemon> team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(boolean nextTurn) {
        this.nextTurn = nextTurn;
    }

    public List<BattlePokemon> getTeam() {
        return team;
    }

    public void setTeam(List<BattlePokemon> team) {
        this.team = team;
    }

    public BattleTrainer(String name, boolean nextTurn) {
        this.name = name;
        this.nextTurn = nextTurn;
    }

    public BattleTrainer(String name, boolean nextTurn, List<BattlePokemon> team) {
        this.name = name;
        this.nextTurn = nextTurn;
        this.team = team;
    }
}
