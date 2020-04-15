package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TheGame implements RoverApi, Serializable {
    private final Mars planetMap;
    private HashMap<String, MyRover> rovers;
    private HashMap<String, MyRover> rovers_bin;
    private final int laserRange;

    public TheGame(Mars planetMap) {
        this.planetMap = planetMap;
        this.rovers = new HashMap<>();
        this.rovers_bin = new HashMap<>();
        this.laserRange = new RandomGame().getRandomLaserRange();
    }

    public MyRover retrieveRoverByPlayer (String player){
        if(rovers.get(player) != null) return rovers.get(player);
        else if(rovers_bin.get(player) != null) return rovers_bin.get(player);
        else return null;
    }

    public Position getPosition(String player){
        return retrieveRoverByPlayer(player).getPosition();
    }

    public LocalMap getPlayerLocalMap(String player, int localMapSize){
        Set<CircularPoint> circularPoints = new MapFuncs().generateLocalMapPoints(retrieveRoverByPlayer(player).getPosition(),planetMap.getSize(), localMapSize);
        Set<MyRover> localRovers = new MapFuncs().comparePointsToRovers(this.rovers, circularPoints);
        Set<Obstacle> localObstacles = new MapFuncs().comparePointsToObstacles(this.planetMap.obstaclePositions(), circularPoints);
        return new LocalMap(localRovers, localObstacles);
    }

    public int getLaserRange(){
        return this.laserRange;
    }
    public boolean isPlayerAlive(String player) {
        return retrieveRoverByPlayer(player).isAlive();
    }
    public void laserShoot(String player){
        retrieveRoverByPlayer(player).move("s");
    }
    public MyRover playerMove(String player ,String command) {
        retrieveRoverByPlayer(player).move(command);
        return retrieveRoverByPlayer(player);
    }

    public MyRover createPlayer(String player){
        MyRover myRover = new RandomGame().generateRandomRover(this, this.planetMap.obstaclePositions(), this.rovers, this.laserRange, this.planetMap, player);
        if (myRover != null) this.rovers.put(player, myRover);
        return myRover;
    }

    public void deletePlayer(String player){
        MyRover rover = retrieveRoverByPlayer(player);
        rover.setAlive(false);
        this.rovers_bin.put(player, rover);
        this.rovers.remove(rover);
    }
}
