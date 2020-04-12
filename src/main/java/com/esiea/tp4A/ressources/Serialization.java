package com.esiea.tp4A.ressources;

import com.esiea.tp4A.roverApi.RandomGame;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Serialization {

    public static void main(String[] args){

        PlayerData playerData = new PlayerData();
        playerData.setName("");
        playerData.setStatus("");

        Gson gson = new Gson();

        String playerJson = gson.toJson(playerData);
        System.out.println(playerJson);

    }
}
