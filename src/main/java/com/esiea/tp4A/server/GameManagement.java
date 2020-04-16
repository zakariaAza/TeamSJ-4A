package com.esiea.tp4A.server;

import com.esiea.tp4A.game.Mars;
import com.esiea.tp4A.game.MyRover;
import com.esiea.tp4A.game.PlayerOuput;
import com.esiea.tp4A.game.TheGame;

import java.util.HashMap;

public class GameManagement {
    private HashMap<String, TheGame> games;
    public GameManagement(HashMap<String, TheGame> games) { this.games = games; }

    public TheGame createGame(String gameName) throws DataAlreadyExistsException {
        try {
            checkGame(gameName);
            throw new DataAlreadyExistsException("Game "+gameName+" already exists.");
        } catch (DataNotFoundException e) {
            TheGame game = new TheGame(gameName,new Mars());
            games.put(gameName, game);//new ServerData().saveGame(gameName, game);
            return game;
        }
    }

    public TheGame checkGame(String gameName) throws DataNotFoundException {
        if (games.get(gameName) == null) throw new DataNotFoundException("Game "+gameName+" not found.");
        else return games.get(gameName);
    }

    public PlayerOuput createRover(String gameName, String playerName) throws DataNotFoundException, DataAlreadyExistsException {
        TheGame game = checkGame(gameName);
        try {
            getRover(game, playerName);
            throw new DataAlreadyExistsException("Player "+playerName+" already exists.");
        } catch (DataNotFoundException e)  {
            MyRover rover = game.createPlayer(playerName);
            games.replace(gameName, game);//new ServerData().saveGame(gameName, game);
            return new PlayerOuput(rover, game.getPlayerLocalMap(rover.getPlayer(), 30));
        }
    }
    public PlayerOuput getRover(TheGame game, String playerName) throws DataNotFoundException {
        MyRover rover = game.retrieveRoverByPlayer(playerName);
        if (rover == null) throw new DataNotFoundException("Player "+playerName+" not found.");
        else return new PlayerOuput(rover, game.getPlayerLocalMap(rover.getPlayer(), 30));
    }

    public PlayerOuput moveRover(String gameName, String playerName, String command) throws DataNotFoundException {
        TheGame game = checkGame(gameName);
        MyRover rover = game.playerMove(playerName, command);
        if(rover == null) throw new DataNotFoundException("Player "+playerName+" not found.");
        PlayerOuput playerOuput = new PlayerOuput(rover, game.getPlayerLocalMap(rover.getPlayer(), 30));
        games.replace(gameName, game);//new ServerData().saveGame(gameName, game);
        return playerOuput;
    }

}
