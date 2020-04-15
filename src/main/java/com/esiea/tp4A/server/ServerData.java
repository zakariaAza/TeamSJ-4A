package com.esiea.tp4A.server;

import com.esiea.tp4A.game.TheGame;

import java.io.*;
import java.util.HashMap;

public class ServerData {
    private final String STAT_FILE = "stats.tmp";

    public HashMap<String, TheGame> getGames() throws IOException, ClassNotFoundException {
        HashMap<String, TheGame> games = new HashMap<>();;
        FileInputStream fis = new FileInputStream(STAT_FILE);
        return checkGames(games, fis);
    }

    public HashMap<String, TheGame> checkGames(HashMap<String, TheGame> games, FileInputStream fis) throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            if (obj instanceof HashMap<?, ?>) games = (HashMap<String, TheGame>) obj;
            else games = new HashMap<>();
            ois.close();
        } catch (EOFException e) { games = new HashMap<>(); }
        return games;
    }

    public void saveGame(String sessionName, TheGame game) throws IOException, ClassNotFoundException {
        HashMap<String, TheGame> games = getGames();
        games.put(sessionName, game);
        FileOutputStream fos = new FileOutputStream(STAT_FILE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(games);
        oos.close();
    }
}
