package com.esiea.tp4A.server;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.ServerSocket;

public class HttpServer {

    public static void main(String[] args) throws IOException {

        final int port = 8080;

        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Listening for connection on port 8080...");
        }catch (IIOException e){
            System.err.println("The port" + port + "is already in used");
        }


        while (true){

        }
    }
}
