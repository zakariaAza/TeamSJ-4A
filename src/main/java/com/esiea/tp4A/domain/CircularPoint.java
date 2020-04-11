package com.esiea.tp4A.domain;

public class CircularPoint {
    private final int x;
    private final int y;

    public CircularPoint(int x, int y, int mapSize) {
        int pos = mapSize/2; int neg = 0-pos+1;
        this.x = x>pos?neg+Math.abs(x-pos)-1:(x<neg?pos-Math.abs(x-neg)+1:x);
        this.y = y>pos?neg+Math.abs(y-pos)-1:(y<neg?pos-Math.abs(y-neg)+1:y);
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
