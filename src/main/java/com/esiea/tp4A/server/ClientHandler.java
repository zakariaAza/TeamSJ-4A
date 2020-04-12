package com.esiea.tp4A.server;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private Input in;

    public ClientHandler(Socket client) { this.client = client; System.out.println("Client created");run();}

    @Override
    public void run() {
        try {
            in = new Input(client.getInputStream(), this);
            in.browseHeader();
        }catch (IOException e){ e.printStackTrace();}
    }

    public void endConnection() {
        try {
            //out.closeConnection();
            in.closeConnnection();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
