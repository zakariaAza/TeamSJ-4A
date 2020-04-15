package com.esiea.tp4A.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.PrivateKey;

public class PlayerOuput {
    private final MyRover player;
    private final LocalMap localMap;

    public PlayerOuput(MyRover player, LocalMap localMap) {
        this.player = player;
        this.localMap = localMap;
    }

    @JsonProperty("player")
    public MyRover getPlayer() {
        return player;
    }

    @JsonProperty("local-map")
    public LocalMap getLocalMap() {
        return localMap;
    }
}
