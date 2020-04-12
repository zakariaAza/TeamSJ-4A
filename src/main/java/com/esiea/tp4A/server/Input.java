package com.esiea.tp4A.server;

import java.io.IOException;
import java.io.InputStream;

public class Input {
    private final ClientHandler handler;
    private final InputStream in;
    boolean stop;
    public Input(InputStream in, ClientHandler handler) throws IOException{
        this.handler = handler;
        this.in = in;
    }

    public void browseHeader() {
        System.out.println("browse start");
        try (ReadBufferedInputStream request = new ReadBufferedInputStream(in)) {
            while (!stop) {
                System.out.println("while");
                String method = request.getMethod();
                switch (method) {
                    case "GET":
                        System.out.println("GET");
                        break;
                    case "POST":
                        System.out.println("POST");
                        break;
                    case "PATCH":
                        System.out.println("PATCH");
                        break;
                }
                stop = !getConnectionMode(request.requestHeader());
                if (stop) {
                    handler.endConnection();
                    System.out.println("End Connection");
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    private boolean getConnectionMode(String requestHeader) {
        String[] headers = requestHeader.split("\n");
        for(int i = 0; i < headers.length; i++) {
            if(headers[i].contains("keep-alive")) {
                return true;
            }
        }
        return false;
    }

    public void closeConnnection() throws IOException {
        in.close();
    }
}
