package com.esiea.tp4A.server;

import com.esiea.tp4A.game.Mars;
import com.esiea.tp4A.game.MyRover;
import com.esiea.tp4A.game.PlayerOuput;
import com.esiea.tp4A.game.TheGame;

import java.io.IOException;
import java.util.HashMap;

public class GameManagement {

    public TheGame createGame(String gameName) throws IOException, ClassNotFoundException, APIAlreadyExistsException {
        try {
            checkGame(gameName);
            throw new APIAlreadyExistsException("Game "+gameName+" already exists.");
        } catch (APINotFoundException e) {
            TheGame game = new TheGame(new Mars());
            new ServerData().saveGame(gameName, game);
            return game;
        }
    }

    public TheGame checkGame(String gameName) throws IOException, ClassNotFoundException, APINotFoundException {
        HashMap<String, TheGame> games = new ServerData().getGames();
        if (games.get(gameName) == null) throw new APINotFoundException("Game "+gameName+" not found.");
        else return games.get(gameName);
    }

    public PlayerOuput createRover(String gameName, String playerName) throws IOException, ClassNotFoundException, APINotFoundException, APIAlreadyExistsException {
        TheGame game = checkGame(gameName);
        try {
            getRover(game, playerName);
            throw new APIAlreadyExistsException("Player already exists.");
        } catch (APINotFoundException e)  {
            MyRover rover = game.createPlayer(playerName);
            new ServerData().saveGame(gameName, game);
            return new PlayerOuput(rover, game.getPlayerLocalMap(rover.getPlayer(), 30));
        }
    }
    public PlayerOuput getRover(TheGame game, String playerName) throws APINotFoundException {
        MyRover rover = game.retrieveRoverByPlayer(playerName);
        if (rover == null) throw new APINotFoundException("Player "+playerName+" not found.");
        else return new PlayerOuput(rover, game.getPlayerLocalMap(rover.getPlayer(), 30));
    }

    public PlayerOuput moveRover(String gameName, String playerName, String command) throws IOException, ClassNotFoundException, APINotFoundException {
        TheGame game = checkGame(gameName);
        MyRover rover = game.playerMove(playerName, command);
        new ServerData().saveGame(gameName, game);
        return new PlayerOuput(rover, game.getPlayerLocalMap(rover.getPlayer(), 30));
    }

}
