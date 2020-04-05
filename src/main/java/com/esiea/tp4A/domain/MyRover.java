package com.esiea.tp4A.domain;

public class MyRover implements MarsRover {
    private Position position;
    private final RoverPosition roverPosition;
    private final PlanetMap mars;
    private final Laser laser;
    public MyRover(int x, int y, Direction direction, int laserRange, PlanetMap planet) {
        this.mars = planet;
        this.roverPosition = new RoverPosition(planet);
        initialize(Position.of(x, y, direction));
        laser = new Laser(laserRange, planet, this);
    }

    @Override
    public Position move(String command) {
        if(command.isEmpty()) return Position.of(0,0, Direction.NORTH);
        for(char singleCommand : command.toCharArray()){
            if(singleCommand == 'f' || singleCommand == 'b' || singleCommand == 'l' || singleCommand == 'r') position = roverPosition.commandSwitch(singleCommand,position);
            else laser.shoot(position);
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

    public Position getPosition() {
        return position;
    }

    public boolean dealShot(Position position){ // doit être remonter pour la 2nd séance
        if(this.mars.isObstacle(position)){
            this.mars.removeObstacle(new Obstacle(position.getX(), position.getY())); return true;
        }else if(new Obstacle(position.getX(), position.getY()).comparePosition(this.position)){ // player hit by his own laser
            return false;
        }else return false;
    }
}
