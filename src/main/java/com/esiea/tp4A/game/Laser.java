package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.Position;

import java.io.Serializable;
import java.util.Set;

public class Laser implements Serializable {
    private final int range;
    private final TheGame game;

    public Laser(int range, TheGame game) {
        this.range = range;
        this.game = game;
    }

    public Position shoot(Position playerPosition){
        Position laser_position = playerPosition;
        RoverPosition roverPosition = new RoverPosition(this.game.getPlanetMap());
        for (int i = 0; i < range; i++) {
            laser_position = roverPosition.forward(laser_position, true);
            if(game.dealShot(laser_position)) return laser_position;
        }
        return laser_position;
    }

    public int getRange() {
        return range;
    }
}
