package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MyRover implements MarsRover , Serializable {
    private Position position; private final RoverPosition roverPosition;
    private final Mars mars; private final TheGame theGame;
    private final Laser laser; private final String player;
    private boolean alive = true;
    public MyRover(TheGame theGame, int x, int y, Direction direction, int laserRange, Mars planet, String player ) {
        this.theGame = theGame; this.player = player;
        this.mars = planet; this.roverPosition = new RoverPosition(planet);
        initialize(Position.of(x, y, direction));
        laser = new Laser(laserRange, planet, this);
    }

    @Override
    public Position move(String command) {
        if(command.isEmpty()) return Position.of(0,0, Direction.NORTH);
        for(char singleCommand : command.toCharArray()){
            if(singleCommand == 'f' || singleCommand == 'b' || singleCommand == 'l' || singleCommand == 'r') position = roverPosition.commandSwitch(singleCommand,position);
            else if(singleCommand == 's') laser.shoot(position);
            else continue;
        }
        return position;
    }

    @Override
    public MarsRover updateMap(PlanetMap map) {
        return this;
    }

    @Override
    public MarsRover initialize(Position position) {
        this.position = position;
        return this;
    }

    @Override
    public MarsRover configureLaserRange(int range) {
        return this;
    }

    @JsonProperty("name")
    public String getPlayer() {
        return player;
    }

    public Position getPosition() {
        return position;
    }

    @JsonProperty("alive")
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }

    @JsonProperty("laser-range")
    public int getLaser() { return laser.getRange(); }

    public boolean dealShot(Position position){
        if(this.mars.isObstacle(position)){
            this.mars.removeObstacle(new Obstacle(position.getX(), position.getY())); return true; // délégation à theGame ?
        }else if(new Obstacle(position.getX(), position.getY()).comparePosition(this.position)){
            this.theGame.deletePlayer(this.player); return true;
        }else return false;
    }
}
