package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.Position;
import com.esiea.tp4A.game.Mars;
import com.esiea.tp4A.game.MyRover;
import com.esiea.tp4A.game.Obstacle;
import com.esiea.tp4A.game.RoverPosition;

import java.util.Set;

public class Laser {
    private final int range;
    private final Mars planet;
    private final RoverPosition roverPosition;
    private final MyRover myRover;

    public Laser(int range, Mars planet, MyRover myRover) {
        this.range = range;
        this.planet = planet;
        this.roverPosition = new RoverPosition(planet);
        this.myRover = myRover;
    }

    public Position shoot(Position roverPosition){
        Position laser_position = roverPosition;
        Set<Obstacle> obstacles = this.planet.obstaclePositions();
        for (int i = 0; i < range; i++) {
            laser_position = this.roverPosition.forward(laser_position, true);
            if(myRover.dealShot(laser_position)) return laser_position;
        }
        return laser_position;
    }
}