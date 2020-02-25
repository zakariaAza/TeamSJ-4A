package com.esiea.tp4A.domain;

public class MyRover implements MarsRover {

    private Position position;

    public MyRover() {
    }

    @Override
    public Position move(String command) {
        if(command.isEmpty()) return Position.of(0,0, Direction.NORTH);
        char singleCommand = command.charAt(0);
        if('f' == singleCommand){
            position = position;
        }
    }
}

