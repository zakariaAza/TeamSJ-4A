package com.esiea.tp4A.domain;

public class MyRover implements MarsRover {

    private Position position;
    private PlanetMap mars;
    private Laser laser;
    public MyRover(int x, int y, Direction direction, PlanetMap planet) {
        updateMap(planet);
        initialize(Position.of(x, y, direction, this.mars));
        laser = new Laser(5);
    }

    @Override
    public Position move(String command) {
        if(command.isEmpty()) return Position.of(0,0, Direction.NORTH, mars);
        //char singleCommand = command.charAt(0);
        for(char singleCommand : command.toCharArray()){
            Position temp_position = position;
            Boolean position_obstacle = false;
            Boolean check_obstacle = true;
            if('f' == singleCommand){
                temp_position = position.forward();
            }else if ('b' == singleCommand){
                temp_position = position.backward();
            }else if ('l' == singleCommand){
                temp_position = position.left();
            }else if ('r' == singleCommand){
                temp_position = position.right();
            }else{
                check_obstacle = false;
                PointXY pointXY = laser.shoot(position);
                if(pointXY != null){
                    if(pointXY.comparePosition(position)){ // player hit by his own laser

                    }else if(this.mars.obstaclePositions().contains(pointXY)){ // obstacles to remove
                        this.mars.removeObstacle(pointXY);
                    }
                }
            }
            if(check_obstacle){
                for(PointXY point : this.mars.obstaclePositions()){
                    if(point.comparePosition(temp_position)) { position_obstacle = true; break;}
                }
            }
            if(!position_obstacle) position = temp_position;
        }
        return position;
    }

    @Override
    public MarsRover updateMap(PlanetMap map) {
        this.mars = map;
        return this;
    }

    @Override
    public MarsRover initialize(Position position) {
        this.position = position;
        return this;
    }

}

