package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarsRoverTest {

    @ParameterizedTest
    @CsvSource({
        "'', 0, 0, NORTH",
        "'f', 0, 0, NORTH",
        "'b', 0, -1, NORTH",
        "'l', 0, 0, WEST",
        "'r', 0, 0, EAST",
        "'ff', 0, 0, NORTH",
        "'lf', -1, 0, WEST",
        "'rf', 1, 0, EAST",
        "'bb', 0, -2, NORTH",
        "'lb', 1, 0, WEST",
        "'llb', 0, 0, SOUTH",
        "'rb', -1, 0, EAST",
        "'fflb', 1, 0, WEST",
    })
    void rover_stays_at_initial_position(String command, int expectedX, int expectedY, Direction expectedDirection){
        Mars mars = new Mars(100, Stream.of(new Obstacle(0,1)).collect(Collectors.toSet()));
        MarsRover marsRover = new MyRover(0,0, Direction.NORTH, 5, mars, "");
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).as("rover_stays_at_initial_position").extracting(Position::getX,Position::getY,Position::getDirection)
            .isEqualTo(List.of(expectedX, expectedY,expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 50, NORTH, 'f', 0, -49, NORTH",
        "50, 0, NORTH, 'rf', -49, 0, EAST",
        "0, 50, SOUTH, 'b', 0, -49, SOUTH",
        "50, 0, SOUTH, 'lf', -49,0, EAST",
        "0, 50, WEST, 'lf', 0, 49, SOUTH",
        "50, 0, WEST, rrf, -49, 0, EAST",
        "0, 50, EAST, rb, 0, -49, SOUTH",
        "50, 0, EAST, 'f', -49, 0, EAST",
        "30, 20, EAST, 'lf', 30, 21, NORTH",
        "-40, 10, WEST, 'lb', -40, 11, SOUTH",
        "25, 0, SOUTH, 'fflb', 24, -2, EAST"
    })

    void rover_limit_positions(int givenX, int givenY, Direction givenDirection, String command, int expectedX, int expectedY, Direction expectedDirection){
        Mars mars = new Mars(100, Stream.of(new Obstacle(0,1)).collect(Collectors.toSet()));
        MarsRover marsRover = new MyRover(givenX,givenY, givenDirection, 5, mars, "");
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).as("rover_limit_positions").extracting(Position::getX,Position::getY,Position::getDirection)
            .isEqualTo(List.of(expectedX, expectedY, expectedDirection));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 50, NORTH, 'f', 0, 50, NORTH", // Obstacle (0, -49)
        "50, 0, NORTH, 'rf', 50, 0, EAST", // Obstacle (-49 , 0)
        "0, 50, SOUTH, 'b', 0, 50, SOUTH", // Obstacle (0, -49)
        "50, 0, SOUTH, 'lf', 50,0, EAST", // Obstacle (-49 , 0)
        "0, 50, WEST, 'lf', 0, 50, SOUTH", // Obstacle (0, -49)
        "50, 0, WEST, 'rrf', 50, 0, EAST", // Obstacle (-49 , 0)
        "-40, 10, WEST, 'lb', -40, 10, SOUTH", // Obstacle (-40, 11)
        "0, 50, EAST, rb, 0, 50, SOUTH", // Obstacle (0, -49)
        "50, 0, EAST, 'f', 50, 0, EAST", // Obstacle (0, -49)
        "25, 0, SOUTH, 'fflb', 25, -2, EAST" // Obstacle (24, -2)
    })

    void rover_detect_obstacles(int givenX, int givenY, Direction givenDirection, String command, int expectedX, int expectedY, Direction expectedDirection){
        Mars mars = new Mars(100, Stream.of(new Obstacle(0,-49),new Obstacle(-49,0),new Obstacle(0,49),new Obstacle(-40,11),new Obstacle(24,-2)).collect(Collectors.toSet()));
        MarsRover marsRover = new MyRover(givenX,givenY, givenDirection, 5, mars, "");
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).as("rover_detect_obstacles").extracting(Position::getX,Position::getY,Position::getDirection)
            .isEqualTo(List.of(expectedX, expectedY, expectedDirection));
    }


    @ParameterizedTest
    @CsvSource({
        "0, 0, NORTH, 'sff', 0, 2, NORTH",
        "0, 50, NORTH, 'srrbb', 0, -48, SOUTH",
        "0, 50, NORTH, 'sf', 0, -49, NORTH",
        "50, 0, NORTH, 'srf', -49, 0, EAST",
        "0, 50, SOUTH, 'sb', 0, -49, SOUTH",
        "50, 0, SOUTH, 'slf', -49,0,EAST",
        "0, 50, WEST, 'slf', 0, 49, SOUTH",
        "50, 0, WEST, 'srrf', -49, 0, EAST",
        "-40, 10, WEST, 'slb', -40, 11, SOUTH",
        "0, 50, EAST, 'srb', 0, -49, SOUTH",
        "50, 0, EAST, 'sf', -49, 0, EAST",
        "25, 0, SOUTH, 'sfflb', 24, -2, EAST"
    })

    void rover_laser_shoot(int givenX, int givenY, Direction givenDirection, String command, int expectedX, int expectedY, Direction expectedDirection){
        Mars mars = new Mars(100, Stream.of(new Obstacle(0,5), new Obstacle(0,6)).collect(Collectors.toSet()));
        MarsRover marsRover = new MyRover(givenX, givenY, givenDirection, 5, mars, "");
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).as("rover_laser_shoot").extracting(Position::getX,Position::getY,Position::getDirection)
            .isEqualTo(List.of(expectedX, expectedY, expectedDirection));
    }
}
