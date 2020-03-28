package com.esiea.tp4A.domain;

public interface    Position {

    int getX();
    int getY();
    Direction getDirection();
    PlanetMap getPlanet();
    Position forward();
    Position backward();
    Position left();
    Position right();
    int generateMapPosition(int position);

    static Position of(int x, int y, Direction direction, PlanetMap planet) { return new FixedPosition(x, y, direction, planet);
    }

    final class FixedPosition implements Position {

        private final int x;
        private final int y;
        private final Direction direction;
        private PlanetMap planet;

        public FixedPosition(int x, int y, Direction direction, PlanetMap planet) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.planet = planet;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public Direction getDirection() {
            return direction;
        }

        @Override
        public PlanetMap getPlanet() {
            return planet;
        }

        @Override
        public Position forward() {
            switch (direction){
                case NORTH:
                    return Position.of(generateMapPosition(x), generateMapPosition(y+1), direction, planet);
                case EAST:
                    return Position.of(generateMapPosition(x+1), generateMapPosition(y), direction, planet);
                case WEST:
                    return Position.of(generateMapPosition(x-1), generateMapPosition(y), direction, planet);
                case SOUTH:
                    return Position.of(generateMapPosition(x), generateMapPosition(y-1), direction, planet);
            }
            throw new IllegalStateException("No Move From Direction : " + direction);
        }

        @Override
        public Position backward() {
            switch (direction){
                case NORTH:
                    return Position.of(generateMapPosition(x), generateMapPosition(y-1), direction, planet);
                case EAST:
                    return Position.of(generateMapPosition(x-1), generateMapPosition(y), direction, planet);
                case WEST:
                    return Position.of(generateMapPosition(x+1), generateMapPosition(y), direction, planet);
                case SOUTH:
                    return Position.of(generateMapPosition(x), generateMapPosition(y+1), direction, planet);
            }
            throw new IllegalStateException("No Move From Direction : " + direction);
        }

        @Override
        public Position left() {
            return Position.of(x, y, direction.left(), planet);
        }

        @Override
        public Position right() {
            return Position.of(x, y, direction.right(), planet);
        }

        @Override
        public int generateMapPosition(int position) {
            if(position > this.planet.getLimit_pos()) return this.planet.getLimit_neg();
            else if(position < this.planet.getLimit_neg()) return this.planet.getLimit_pos();
            else return position;
        }

    }
}
