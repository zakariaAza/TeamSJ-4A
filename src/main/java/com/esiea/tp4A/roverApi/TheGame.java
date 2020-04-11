package com.esiea.tp4A.roverApi;

import com.esiea.tp4A.domain.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TheGame implements RoverApi{
    private final PlanetMap planetMap;
    private Set<MyRover> rovers;
    private final int laserRange;

    public TheGame(PlanetMap planetMap) {
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
            if (rover.getPlayer().equals(player)) return rover;
        } return null;
    }

    public Position getPosition(String player){
        return retrieveRoverByPlayer(player).getPosition();
    }

    public LocalMap getPlayerLocalMap(String player){
        Set<CircularPoint> circularPoints = new MapFuncs().generateLocalMapPoints(retrieveRoverByPlayer(player).getPosition(),planetMap.getSize(), 30);
        Set<MyRover> localRovers = new MapFuncs().comparePointsToRovers(this.rovers, circularPoints);
        Set<Obstacle> localObstacles = new MapFuncs().comparePointsToObstacles(this.planetMap.obstaclePositions(), circularPoints);
        return new LocalMap(localRovers, localObstacles);
    }

    public int getLaserRange(){
        return this.laserRange;
    }

    public boolean isPlayerAlive(String player) {
        return retrieveRoverByPlayer(player)!=null;
    }

    public void laserShoot(String player){
        retrieveRoverByPlayer(player).move("s");
    }

    public Position playerMove(String player ,String command) {
        return retrieveRoverByPlayer(player).move(command);
    }

    public MyRover createPlayer(String player){
        MyRover myRover = new RandomGame().generateRandomRover(this, this.planetMap.obstaclePositions(), this.rovers, this.laserRange, this.planetMap, player);
        this.rovers.add(myRover);
        return myRover;
    }

    public void deletePlayer(String player){
        this.rovers.remove(retrieveRoverByPlayer(player));
    }
}
