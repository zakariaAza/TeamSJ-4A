package com.esiea.tp4A.domain;

public class MyRover implements MarsRover {
    private Position position;
    private RoverPosition roverPosition;
    private PlanetMap mars;
    private Laser laser;
    public MyRover(int x, int y, Direction direction, PlanetMap planet) {
        updateMap(planet);
        initialize(Position.of(x, y, direction));
        laser = new Laser(5, planet, this);
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
        this.mars = map; this.roverPosition = new RoverPosition(map);
        return this;
    }

    @Override
    public MarsRover initialize(Position position) {
        this.position = position;
        return this;
    }

    public boolean dealShot(Position position){ // doit être remonter pour la 2nd séance
        if(this.mars.isObstacle(position)){
            this.mars.removeObstacle(new PointXY(position.getX(), position.getY())); return true;
        }else if(new PointXY(position.getX(), position.getY()).comparePosition(this.position)){ // player hit by his own laser
            return false;
        }else return false;
    }
}
