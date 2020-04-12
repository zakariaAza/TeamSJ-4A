package com.esiea.tp4A.roverApi;

import com.esiea.tp4A.domain.Mars;
import com.esiea.tp4A.domain.MyRover;
import com.esiea.tp4A.domain.Obstacle;
import com.esiea.tp4A.domain.PlanetMap;

import java.util.HashSet;
import java.util.Set;

public class random_functions_test {
    public static void main(String[] args) {
        RandomGame randomGame = new RandomGame();
        int mapsize = randomGame.getRandomMapSize();
        int laserRange = randomGame.getRandomLaserRange();
        Set<Obstacle> obstacles = randomGame.generateObstaclesPosition(mapsize,(0-(mapsize/2)+1), mapsize/2);
        Mars planetMap = new Mars(mapsize, obstacles);
        TheGame theGame = new TheGame(planetMap);
        Set<MyRover> rovers = new HashSet<>();
        System.out.println(mapsize);
        /*for(Obstacle obstacle : obstacles){
                System.out.println("("+obstacle.getX()+","+obstacle.getY()+")");
        }
        System.out.println(obstacles.size());*/
        String player;
        for(int i = 0; i < 50; i++){
                player = String.valueOf(i);
                MyRover rover = randomGame.generateRandomRover(theGame, obstacles, rovers, laserRange, planetMap, player);
                if(rover != null){
                    //System.out.println("("+rover.getPosition().getX()+","+rover.getPosition().getY()+") "+rover.getPosition().getDirection());
                    rovers.add(rover);
                }
        }
        for(MyRover rover : rovers){
            System.out.println("("+rover.getPosition().getX()+","+rover.getPosition().getY()+") "+rover.getPosition().getDirection());
        }
        System.out.println(rovers.size());

    }
}
