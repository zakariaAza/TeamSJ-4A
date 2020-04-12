package com.esiea.tp4A;

import com.esiea.tp4A.domain.CircularPoint;
import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircularPointTest {
final private  CircularPoint cP = new CircularPoint(1, 2, 10);

    @Test
    void test_cicular_get() {
        Assertions.assertThat(cP.getX()).as("CircularPointX").isEqualTo(1);
        Assertions.assertThat(cP.getY()).as("CircularPointY").isEqualTo(2);
    }

    @Test
    void test_cicular_constructor() {
        CircularPoint cP2 = new CircularPoint(-5, 6, 10);
        Assertions.assertThat(cP2.getX()).as("CircularPointX2").isEqualTo(5);
        Assertions.assertThat(cP2.getY()).as("CircularPointY3").isEqualTo(-4);

    }

    @Test
    void test_circular_constructor2() {
        CircularPoint cP3 = new CircularPoint(6, -5, 10);
        Assertions.assertThat(cP3.getX()).as("CircularPointX3").isEqualTo(-4);
        Assertions.assertThat(cP3.getY()).as("CircularPointY4").isEqualTo(5);
    }

    @Test
    void test_circular_position() {
        Position p = Position.of(1, 2, Direction.WEST);
        Assertions.assertThat(cP.comparePosition(p)).as("comparePosition").isEqualTo(true);
        Position p2 = Position.of(2, 2, Direction.WEST);
        Assertions.assertThat(cP.comparePosition(p2)).as("comparePosition").isEqualTo(false);
        Position p3 = Position.of(1, 1, Direction.WEST);
        Assertions.assertThat(cP.comparePosition(p3)).as("comparePosition").isEqualTo(false);
        Position p4 = Position.of(2, 3, Direction.WEST);
        Assertions.assertThat(cP.comparePosition(p4)).as("comparePosition").isEqualTo(false);
    }
}
