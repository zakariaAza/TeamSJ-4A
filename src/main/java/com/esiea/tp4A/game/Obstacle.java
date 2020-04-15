package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.Position;

import java.io.Serializable;

public class Obstacle implements Serializable {
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
