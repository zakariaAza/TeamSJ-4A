package com.esiea.tp4A.game;

import java.util.Set;

public class LocalMap{
    private final Set<MyRover> rovers;
    private final Set<Obstacle> obstacles;

    public LocalMap(Set<MyRover> rovers, Set<Obstacle> obstacles) {
        this.rovers = rovers;
        this.obstacles = obstacles;
    }
    public Set<MyRover> getPlayers() { return rovers; }

    public Set<Obstacle> getObstacles() {
        return obstacles;
    }
}
