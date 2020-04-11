package com.esiea.tp4A.roverApi;

import com.esiea.tp4A.domain.MarsRover;
import com.esiea.tp4A.domain.MyRover;
import com.esiea.tp4A.domain.Obstacle;
import com.esiea.tp4A.domain.Position;

import java.util.HashSet;
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
