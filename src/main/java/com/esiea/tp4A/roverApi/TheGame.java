package com.esiea.tp4A.roverApi;

import com.esiea.tp4A.domain.Mars;
import com.esiea.tp4A.domain.MyRover;
import com.esiea.tp4A.domain.Obstacle;
import com.esiea.tp4A.domain.PlanetMap;

import java.util.HashSet;
import java.util.Set;

public class TheGame {

    private final PlanetMap planetMap;
    private Set<MyRover> rovers;
    private final int laserRange;

    public TheGame(PlanetMap planetMap, Set<MyRover> rovers) {
        this.rovers = rovers;
        RandomGame randomGame = new RandomGame();
        int mapsize = randomGame.getRandomMapSize();
        this.laserRange = randomGame.getRandomLaserRange();
        Set<Obstacle> obstacles = randomGame.generateObstaclesPosition(mapsize,(0-(mapsize/2)+1), mapsize/2);
        this.planetMap = new Mars(mapsize, obstacles);
        this.rovers = new HashSet<>();
    }
}
