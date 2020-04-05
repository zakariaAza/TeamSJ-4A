package com.esiea.tp4A.domain;

import java.util.HashSet;
import java.util.Set;

public class Mars implements PlanetMap {

    private int size = 100;
    private int limit_pos = 0;
    private int limit_neg = 0;
    private Set<PointXY> obstacles = new HashSet<>();

    public Mars(int size, Set<PointXY> obstacles) {
        this.size = size;
        this.limit_pos = size/2;
        this.limit_neg = 0 - this.limit_pos + 1;
        this.obstacles = obstacles;
    }

    @Override
    public Set<PointXY> obstaclePositions() {
        return this.obstacles;
    }

    public void removeObstacle(PointXY pointXY){
        this.obstacles.remove(pointXY);
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

    @Override
    public boolean isObstacle(Position temp_position) {
        for(PointXY point : this.obstacles){
            if(point.comparePosition(temp_position)) return true;
        }
        return false;
    }
}
