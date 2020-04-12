package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import com.esiea.tp4A.roverApi.TheGame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class ObstacleTest {

    @Test
    void test_obstacle_get() {
        Obstacle obstacle = new Obstacle(1, 2);
        Assertions.assertThat(obstacle.getX()).as("ObstaclePointX").isEqualTo(1);
        Assertions.assertThat(obstacle.getY()).as("ObstaclePointY").isEqualTo(2);
    }

}
