package com.esiea.tp4A.ressources;

import java.io.Serializable;

public class PlayerData implements Serializable {

    public String name;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}




