package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import com.esiea.tp4A.roverApi.TheGame;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class MapFuncsTest {
    private final MapFuncs mapFuncs = new MapFuncs();

    @Test
    void localPoints(){
        int mapSize = 100;
        Position position = Position.of(0, 0, Direction.NORTH);
        Set<CircularPoint> resultsPoints = mapFuncs.generateLocalMapPoints(position, mapSize, 1);
        for (CircularPoint point : resultsPoints){
            assertThat(point.getX()).as("LocalPoints").isEqualTo(0);
            assertThat(point.getY()).as("LocalPoints").isEqualTo(0);
        }
    }

    @Test
    void obstaclesComparaison(){
        Set<Obstacle> obstacles = mapFuncs.comparePointsToObstacles(Stream.of(new Obstacle(0,1)).collect(Collectors.toSet()), Stream.of(new CircularPoint(0,1,100)).collect(Collectors.toSet()));
        for(Obstacle obstacle : obstacles){
            assertThat(obstacle.getX()).as("ObstaclePoint").isEqualTo(0);
            assertThat(obstacle.getY()).as("ObstaclePoint").isEqualTo(1);
        }
    }

    @Test
    void roversComparaison(){
        Mars mars = new Mars(100, Stream.of(new Obstacle(0,1)).collect(Collectors.toSet()));
        MyRover myRover = new MyRover(new TheGame(mars), 0, 1, Direction.NORTH, 5, mars, "");
        Set<MyRover> rovers = mapFuncs.comparePointsToRovers(Stream.of(myRover).collect(Collectors.toSet()), Stream.of(new CircularPoint(0,1,100)).collect(Collectors.toSet()));
            for(MyRover rover : rovers){
                assertThat(rover.getPosition().getX()).as("RoverPositionX").isEqualTo(0);
                assertThat(rover.getPosition().getY()).as("RoverPositionY").isEqualTo(1);
            }
    }


}
