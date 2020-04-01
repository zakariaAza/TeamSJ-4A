package com.esiea.tp4A.domain;

import java.util.Set;

public class Laser {
    private int range;

    public Laser(int range) {
        this.range = range;
    }

    public PointXY shoot(Position roverPosition){
        Position laser_position = roverPosition;
        Set<PointXY> obstacles = roverPosition.getPlanet().obstaclePositions();

        for (int i = 0; i < range; i++) {
            laser_position = laser_position.forward();
            PointXY pointXY = new PointXY(laser_position.getX(), laser_position.getY());
            for (PointXY pXY : obstacles) {
                if(pXY.comparePosition(laser_position)) return pXY;
            }
            //if(obstacles.contains(pointXY)) return pointXY;
            if(pointXY.comparePosition(roverPosition)) return new PointXY(roverPosition.getX(), roverPosition.getY());
        }
        return null;
    }
}
