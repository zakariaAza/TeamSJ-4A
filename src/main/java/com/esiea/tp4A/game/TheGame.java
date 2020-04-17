package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TheGame implements RoverApi, Serializable {
    private final Mars planetMap;
    private HashMap<String, MyRover> rovers = new HashMap<>();
    private HashMap<String, MyRover> rovers_bin = new HashMap<>();
    private final int laserRange;
    private final String name;

    public TheGame(String name, Mars planetMap) {
        this.name = name;
        this.planetMap = planetMap;
        this.laserRange = new RandomGame().getRandomLaserRange();
    }

    public String getName() { return name; }

    @JsonProperty("players")
    public HashMap<String, MyRover> getRovers() { return rovers; }

    @JsonProperty("Mars")
    public Mars getPlanetMap() { return planetMap; }

    public MyRover retrieveRoverByPlayer (String player){
        if(rovers.get(player) != null) return rovers.get(player);
        else if(rovers_bin.get(player) != null) return rovers_bin.get(player);
        else return null;
    }

    public MyRover checkPlayerByPosition(Position position){
        for (Map.Entry<String, MyRover> entry : this.rovers.entrySet()) {
            if(entry.getValue().getPosition().getX()==position.getX() && entry.getValue().getPosition().getY()==position.getY()) return entry.getValue();
        }return null;
    }

    public void rearrangePlayers(){
        for (Map.Entry<String, MyRover> entry : this.rovers.entrySet()) {
            if(!entry.getValue().isAlive()) {
                this.rovers_bin.put(entry.getValue().getPlayer(), entry.getValue());
                this.rovers.remove(entry.getValue());
            }
        }
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
        rearrangePlayers();
        if(retrieveRoverByPlayer(player) != null) retrieveRoverByPlayer(player).move(command);
        return retrieveRoverByPlayer(player);
    }

    public MyRover createPlayer(String player){
        MyRover myRover = new RandomGame().generateRandomRover(this, this.planetMap.obstaclePositions(), this.rovers, this.laserRange, this.planetMap, player);
        if (myRover != null) this.rovers.put(player, myRover);
        return myRover;
    }

    public void addPlayer(MyRover rover){
        if(retrieveRoverByPlayer(rover.getPlayer()) == null) this.rovers.put(rover.getPlayer(), rover);
    }

    public boolean dealShot(Position position){
        if(this.planetMap.isObstacle(position)){
            this.planetMap.removeObstacle(new Obstacle(position.getX(), position.getY())); return true; // délégation à theGame ?
        }else if(checkPlayerByPosition(position) != null){
            deletePlayer(checkPlayerByPosition(position).getPlayer()); return true;
        }else return false;
    }

    public void deletePlayer(String player){
        MyRover rover = retrieveRoverByPlayer(player);
        rover.setAlive(false);
    }
}
