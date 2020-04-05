package com.esiea.tp4A.domain;

public class Obstacle {
    private final int x;
    private final int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean comparePosition(Position position){
        return (position.getX() == x && position.getY() == y);
    }

}
