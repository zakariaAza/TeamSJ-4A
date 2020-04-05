package com.esiea.tp4A.domain;

public class RoverPosition{
    private final PlanetMap planet;
    public RoverPosition(PlanetMap planet) {
        this.planet = planet;
    }

    public Position commandSwitch(char command, Position position){
        switch (command){
            case 'f' : return forward(position, false);
            case 'b' : return backward(position);
            case 'l' : return left(position);
            case 'r' : return right(position);
            default: return null;
        }
    }

    public Position forward(Position position, boolean passCheck) {
        Position calc_position;
        switch (position.getDirection()){
            case NORTH: calc_position = Position.of(generateMapPosition(position.getX()), generateMapPosition(position.getY()+1), position.getDirection()); break;
            case EAST: calc_position = Position.of(generateMapPosition(position.getX()+1), generateMapPosition(position.getY()), position.getDirection()); break;
            case WEST: calc_position = Position.of(generateMapPosition(position.getX()-1), generateMapPosition(position.getY()), position.getDirection()); break;
            case SOUTH: calc_position = Position.of(generateMapPosition(position.getX()), generateMapPosition(position.getY()-1), position.getDirection()); break;
            default: calc_position = null;
        }
        if(calc_position == null)throw new IllegalStateException("No Move From Direction : " + position.getDirection());
        if(passCheck) return calc_position;
        else return checkObstacle(position, calc_position);
    }
    public Position backward(Position position) {
        switch (position.getDirection()){
            case NORTH: return checkObstacle(position, Position.of(generateMapPosition(position.getX()), generateMapPosition(position.getY()-1), position.getDirection()));
            case EAST: return checkObstacle(position, Position.of(generateMapPosition(position.getX()-1), generateMapPosition(position.getY()), position.getDirection()));
            case WEST: return checkObstacle(position, Position.of(generateMapPosition(position.getX()+1), generateMapPosition(position.getY()), position.getDirection()));
            case SOUTH: return checkObstacle(position, Position.of(generateMapPosition(position.getX()), generateMapPosition(position.getY()+1), position.getDirection()));
        }
        throw new IllegalStateException("No Move From Direction : " + position.getDirection());
    }

    public Position left(Position position) { return checkObstacle(position, Position.of(position.getX(), position.getY(), position.getDirection().left())); }

    public Position right(Position position) { return checkObstacle(position, Position.of(position.getX(), position.getY(), position.getDirection().right())); }

    public Position checkObstacle(Position initial_position, Position calculated_position){
        if(this.planet.isObstacle(calculated_position)) return initial_position;
        else return calculated_position;
    }

    public int generateMapPosition(int position) {
        if(position > this.planet.getLimit_pos()) return this.planet.getLimit_neg();
        else if(position < this.planet.getLimit_neg()) return this.planet.getLimit_pos();
        else return position;
    }
}
