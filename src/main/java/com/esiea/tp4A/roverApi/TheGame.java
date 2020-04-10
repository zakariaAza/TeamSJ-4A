package com.esiea.tp4A.roverApi;

import com.esiea.tp4A.domain.*;

import java.util.HashSet;
import java.util.Iterator;
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

    MyRover retrieveRoverByPlayer ( String player){
        for (Iterator<MyRover> it = rovers.iterator(); it.hasNext(); ) {
            MyRover rover = it.next();
            if (rover.getPlayer().equals(player)){
                return rover;
            }
        } return null;
    }

    Position getPosition(String player){
        return retrieveRoverByPlayer(player).getPosition();
    }

    LocalMap getPlayerLocalMap(String player){
        return null;
    }

    int getLaserRange(){
        return laserRange;
    }

    void laserShoot(String player){
        Position playerPosition = retrieveRoverByPlayer(player).getPosition();
        retrieveRoverByPlayer(player).getLaser().shoot(playerPosition);
    }

    Position playerMove(String player ,String command)
    {
        return retrieveRoverByPlayer(player).move(command);
    }


}
