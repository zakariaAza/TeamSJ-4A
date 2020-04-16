package com.esiea.tp4A;

import com.esiea.tp4A.domain.*;
import com.esiea.tp4A.game.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

public class ThaGameTest {
    private final RandomGame randomGame = new RandomGame();
    private final int mapsize = randomGame.getRandomMapSize();
    private final Set<Obstacle> obstacles = randomGame.generateObstaclesPosition(mapsize,(0-(mapsize/2)+1), mapsize/2);
    private final Mars planetMap = new Mars(mapsize, obstacles);
    private final TheGame theGame = new TheGame("game",planetMap);

   @Test
   void checkPlayerPosition(){
       theGame.createPlayer("Player1");
       Position position = theGame.getPosition("Player1");
       assertThat(position.getDirection()).as("checkPlayerPosition").isIn(Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH);
   }

   @Test
    void checkPlayerLaserRange(){
       int laserRange = theGame.getLaserRange();
       assertThat(laserRange).as("checkPlayerPosition").isIn(5, 30, 601);
   }

    @Test
    void checkIsPlayerAlive(){
        theGame.createPlayer("Player2");
        Boolean alive = theGame.isPlayerAlive("Player2");
        assertThat(alive).as("checkIsPlayerAlive").isEqualTo(true);
        theGame.deletePlayer("Player2");
        alive = theGame.isPlayerAlive("Player2");
        assertThat(alive).as("checkIsPlayerAlive").isEqualTo(false);
    }

    @Test
    void checkPlayerMove(){
        theGame.createPlayer("Player3");
        MyRover rover = theGame.playerMove("Player3", "f");
        assertThat(rover.getPlayer()).as("checkPlayerMove").isEqualTo("Player3");
    }

    @Test
    void checkLocalMap(){
        MyRover myRover = theGame.createPlayer("Player4");
        LocalMap localMap = theGame.getPlayerLocalMap("Player4", 1);
        assertThat(localMap.getObstacles().size()).as("getObstacles").isIn(0,1);
        for (MyRover rover : localMap.getPlayers()){
            assertThat(rover.getPosition().getX()).as("getPlayers").isEqualTo(myRover.getPosition().getX());
        }
    }

    @Test
    void checkLaserShoot(){
        theGame.createPlayer("Player5");
        theGame.laserShoot("Player5");
        Boolean alive = theGame.isPlayerAlive("Player5");
        assertThat(alive).as("checkIsPlayerAlive").isIn(true, false);
    }

    @Test
    void checkUnknownPlayer(){
        MyRover myRover = theGame.retrieveRoverByPlayer("unknown");
        assertThat(myRover).as("checkUnknownPlayer").isNull();
    }

    @Test
    void checkOverLoadRovers(){
        for(int i = 0; i < 50; i++){
            theGame.createPlayer("player"+i);
        }
        MyRover myRover = theGame.createPlayer("player55");
        assertThat(myRover).as("checkOverLoadRovers").isNull();
    }

}
