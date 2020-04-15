package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.PlanetMap;
import com.esiea.tp4A.domain.Position;
import com.esiea.tp4A.game.Obstacle;

import java.util.HashSet;
import java.util.Set;

public class Mars implements PlanetMap {

    private final int size;
    private final int limit_pos;
    private final int limit_neg;
    private Set<Obstacle> obstacles = new HashSet<>();

    public Mars(int size, Set<Obstacle> obstacles) {
        this.size = size;
        this.limit_pos = size/2;
        this.limit_neg = 0 - this.limit_pos + 1;
        this.obstacles = obstacles;
    }

    @Override
    public Set<Obstacle> obstaclePositions() {
        return this.obstacles;
    }

    public void removeObstacle(Obstacle obstacle){
        this.obstacles.remove(obstacle);
    }

    public int getSize() {
        return size;
    }

    public int getLimit_pos() {
        return limit_pos;
    }

    public int getLimit_neg() {
        return limit_neg;
    }

    public boolean isObstacle(Position temp_position) {
        for(Obstacle point : this.obstacles){
            if(point.comparePosition(temp_position)) return true;
        }
        return false;
    }
}
