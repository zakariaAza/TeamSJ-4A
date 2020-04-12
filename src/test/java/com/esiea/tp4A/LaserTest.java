package com.esiea.tp4A;

import com.esiea.tp4A.domain.Laser;
import com.esiea.tp4A.domain.Mars;
import com.esiea.tp4A.domain.Obstacle;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LaserTest {
    private final Mars mars = new Mars(100, Stream.of(new Obstacle(0,5), new Obstacle(0,6)).collect(Collectors.toSet()));
    //private final Laser laser = new Laser();

}
