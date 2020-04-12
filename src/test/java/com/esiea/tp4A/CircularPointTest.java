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
    void test_circular_position() {
        Position p = Position.of(1, 2, Direction.WEST);
        Assertions.assertThat(cP.comparePosition(p)).as("comparePosition").isEqualTo(true);
    }
}
