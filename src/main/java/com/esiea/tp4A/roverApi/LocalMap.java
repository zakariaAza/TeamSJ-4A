package com.esiea.tp4A.roverApi;

import com.esiea.tp4A.domain.Obstacle;
import com.esiea.tp4A.domain.Position;

import java.util.HashSet;
import java.util.Set;

public class LocalMap {
    private Set<Position> playerPosiitons = new HashSet<>();
    private Set<Obstacle> obstacles = new HashSet<>();

    public LocalMap(Set<Position> playerPosiitons, Set<Obstacle> obstacles) {
        this.playerPosiitons = playerPosiitons;
        this.obstacles = obstacles;
    }

    public Set<Position> getPlayerPosiitons() {
        return playerPosiitons;
    }

    public Set<Obstacle> getObstacles() {
        return obstacles;
    }
}
