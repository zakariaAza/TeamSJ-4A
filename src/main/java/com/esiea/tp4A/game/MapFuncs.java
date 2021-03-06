package com.esiea.tp4A.game;

import com.esiea.tp4A.domain.Position;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MapFuncs implements Serializable {

    public Set<CircularPoint> generateLocalMapPoints(Position rover, int mapSize, int localMapSize){
        int halfMapSize = localMapSize/2;
        CircularPoint start = new CircularPoint(rover.getX()-halfMapSize,rover.getY()-halfMapSize, mapSize);
        int x = start.getX(); int y = start.getY();
        Set<CircularPoint> points = new HashSet<>();
        for (int i = 0; i < localMapSize; i++){
            for (int j = 0; j < localMapSize; j++){
                points.add(new CircularPoint(x+i, y+j, mapSize));
            }
        }return points;
    }

    public Set<Obstacle> comparePointsToObstacles(Set<Obstacle> obstacles, Set<CircularPoint> circularPoints){
        Set<Obstacle> localObstacles = new HashSet<>();
        for (CircularPoint point : circularPoints){
            for (Obstacle obstacle: obstacles) {
                if(point.getX() == obstacle.getX() && point.getY() == obstacle.getY()) localObstacles.add(obstacle);
            }
        }
        return localObstacles;
    }

    public Set<MyRover> comparePointsToRovers(HashMap<String, MyRover> rovers, Set<CircularPoint> circularPoints){
        Set<MyRover> localRovers = new HashSet<>();
        for (CircularPoint point : circularPoints){
            rovers.forEach((playerName, rover) -> {
                if(point.comparePosition(rover.getPosition())) localRovers.add(rover);
            });/*for (MyRover rover: rovers) { if(point.comparePosition(rover.getPosition())) localRovers.add(rover); }*/
        }
        return localRovers;
    }
}
