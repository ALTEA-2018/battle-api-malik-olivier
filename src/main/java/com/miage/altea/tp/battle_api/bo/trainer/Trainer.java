package com.miage.altea.tp.battle_api.bo.trainer;

import java.util.List;

public class Trainer {

    private String name;

    private List<Pokemon> team;

    private String password;

    public Trainer(String name) {
        this.name = name;
    }

    public Trainer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
