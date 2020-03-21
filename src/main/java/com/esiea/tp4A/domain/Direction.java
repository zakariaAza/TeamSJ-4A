package com.esiea.tp4A.domain;

public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    public Direction left(){
        int leftOrdinal = ordinal()-1;
        Direction[] values = Direction.values();
        return values[leftOrdinal < 0 ? values.length - 1 : leftOrdinal];
    }

    public Direction right(){
        int rightOrdinal = ordinal()+1;
        Direction[] values = Direction.values();
        return values[rightOrdinal > values.length - 1 ?  0 : rightOrdinal];
    }

}
