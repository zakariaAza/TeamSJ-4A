package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarsRoverTest {

    @ParameterizedTest
    @CsvSource({
        "'', 0, 0, NORTH",
        "'f', 0, 1, NORTH",
        "'b', 0, -1, NORTH",
        "'l', 0, 0, WEST",
        "'r', 0, 0, EAST",
        "'ff', 0, 2, NORTH",
        "'lf', -1, 0, WEST",
        "'rf', 1, 0, EAST",
        "'bb', 0, -2, NORTH",
        "'lb', 1, 0, WEST",
        "'llb', 0, 1, SOUTH",
        "'rb', -1, 0, EAST",
        "'fflb', 1, 2, WEST",
    })
    void rover_stays_at_initial_position(String command, int expectedX, int expectedY, Direction expectedDirection){
        Mars mars = new Mars(100, Stream.of(new PointXY(0,1)).collect(Collectors.toSet()));
        MarsRover marsRover = new MyRover(0,0, Direction.NORTH,mars);
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
        Mars mars = new Mars(100, Stream.of(new PointXY(0,1)).collect(Collectors.toSet()));
        MarsRover marsRover = new MyRover(givenX,givenY, givenDirection, mars);
        Position newPosition = marsRover.move(command);
        Assertions.assertThat(newPosition).as("rover_limit_positions").extracting(Position::getX,Position::getY,Position::getDirection)
            .isEqualTo(List.of(expectedX, expectedY, expectedDirection));
    }

    @Test
    void rover_laser_shoot(){
        Mars mars = new Mars(100, Stream.of(new PointXY(0,5)).collect(Collectors.toSet()));
        MarsRover marsRover = new MyRover(0,0, Direction.NORTH, mars);
        Position newPosition = marsRover.move("sfffff");
        Assertions.assertThat(newPosition).as("rover_laser_shoot").extracting(Position::getX,Position::getY,Position::getDirection)
            .isEqualTo(List.of(0, 5, Direction.NORTH));
    }
}
