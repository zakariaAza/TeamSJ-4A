package com.esiea.tp4A;

import com.esiea.tp4A.game.PlayerOuput;
import com.esiea.tp4A.game.TheGame;
import com.esiea.tp4A.server.DataAlreadyExistsException;
import com.esiea.tp4A.server.DataNotFoundException;
import com.esiea.tp4A.server.GameManagement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;

public class GameManagementTest {
    private GameManagement gameManagement = new GameManagement(new HashMap<String, TheGame>());

    @Test
    void gameCreateTest() throws DataAlreadyExistsException, IOException, ClassNotFoundException {
        try {
            TheGame theGame = gameManagement.createGame("first");
            assertThat(theGame.getName()).as("gameCreateTest").isEqualTo("first");
        } catch (DataAlreadyExistsException e) {
            assertThat(e).hasNoCause().hasMessage("Game first already exists.");
        }
    }

    @Test
    void checkGameTest() throws DataNotFoundException, IOException, ClassNotFoundException {
        try {
            TheGame theGame = gameManagement.checkGame("first");
            assertThat(theGame.getName()).as("gameCreateTest").isEqualTo("first");
        } catch (DataNotFoundException e) {
            assertThat(e).hasNoCause().hasMessage("Game first not found.");
        }
    }


    @Test
    void checkCreateRoverTest() {
        try {
            gameManagement.createGame("second");
        } catch (DataAlreadyExistsException e) {
            assertThat(e).hasNoCause().hasMessage("Game first already exists.");
        }
        try {
            PlayerOuput myRover = gameManagement.createRover("second", "rover");
            assertThat(myRover.getPlayer().getPlayer()).as("playerName").isEqualTo("rover");
        } catch (DataNotFoundException e) {
            assertThat(e).hasNoCause().hasMessage("Player rover not found.");
        } catch (DataAlreadyExistsException e) {
            assertThat(e).hasNoCause().hasMessage("Player rover already exists.");
        }
    }

}
