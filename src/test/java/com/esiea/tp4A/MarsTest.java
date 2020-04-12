package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;
import com.esiea.tp4A.domain.Mars;
import com.esiea.tp4A.domain.Obstacle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarsTest {

    @Test
    void Mars_test(){
        Mars planetMap = new Mars(50, Stream.of(new Obstacle(0,1)).collect(Collectors.toSet()));
        Assertions.assertThat(planetMap.getSize()).as("MarsTest").isEqualTo(50);
    }
}
