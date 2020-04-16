package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import com.esiea.tp4A.game.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LocalMapTest {

    @Test
    void LocalMap_test(){
        Mars mars = new Mars(100, Stream.of(new Obstacle(0,1)).collect(Collectors.toSet()));

        Set<MyRover> roverSet = new HashSet<>();
        roverSet.add( new MyRover(new TheGame("test",mars), 0, 1, Direction.NORTH, 5, mars, ""));

        Set<Obstacle> obstacleSet = new HashSet<>();
        obstacleSet.add( new Obstacle(0,1) );

        LocalMap LocalMap = new LocalMap( roverSet, obstacleSet ) ;
        for(Obstacle obstacle : LocalMap.getObstacles()){
            assertThat(obstacle.getX()).as("ObstaclePoint").isEqualTo(0);
            assertThat(obstacle.getY()).as("ObstaclePoint").isEqualTo(1);
        }

        for(MyRover rover : LocalMap.getPlayers()){
            assertThat(rover.getPosition().getX()).as("RoverPositionX").isEqualTo(0);
            assertThat(rover.getPosition().getY()).as("RoverPositionY").isEqualTo(1);
        }
    }
}

