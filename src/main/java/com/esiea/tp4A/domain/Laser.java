package com.esiea.tp4A.domain;

import java.util.Set;

public class Laser {
    private final int range;
    private final PlanetMap planet;
    private final RoverPosition roverPosition;
    private final MyRover myRover;

    public Laser(int range, PlanetMap planet, MyRover myRover) {
        this.range = range;
        this.planet = planet;
        this.roverPosition = new RoverPosition(planet);
        this.myRover = myRover;
    }

    public void shoot(Position roverPosition){
        Position laser_position = roverPosition;
        Set<PointXY> obstacles = this.planet.obstaclePositions();
        for (int i = 0; i < range; i++) {
            laser_position = this.roverPosition.forward(laser_position, true);
            if(myRover.dealShot(laser_position)) return;
        }
    }

    /*private PointXY moveLaserPoint(Position roverPosition, Position laser_position, Set<PointXY> obstacles){
        for (int i = 0; i < range; i++) {
            laser_position = laser_position.forward();
            PointXY pointXY = new PointXY(laser_position.getX(), laser_position.getY());
            for (PointXY pXY : obstacles) if(pXY.comparePosition(laser_position)) return pXY;
            if(pointXY.comparePosition(roverPosition)) return new PointXY(roverPosition.getX(), roverPosition.getY());
        }
        return null;
    }*/
}
