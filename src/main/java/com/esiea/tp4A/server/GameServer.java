package com.esiea.tp4A.server;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread{
    private boolean stop = false;
    private final int port = 1234;

    public static void main(String[] args) throws IOException {
        new GameServer();
        //System.out.println("MAIN");
    }

    public GameServer() throws IOException{
        this.start();
        //run();
    }

    @Override
    public void run() {
        System.out.println("RUN");
        try (ServerSocket serverSocket = new ServerSocket(port)){
            while (!stop) {
                Socket client = serverSocket.accept();
                new Thread(new ClientHandler(client)).start();
                //new ClientHandler(client);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public synchronized void finish() {
        stop = true;
    }
}
