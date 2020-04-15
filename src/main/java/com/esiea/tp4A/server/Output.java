package com.esiea.tp4A.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class Output {
    PrintWriter os;
    BufferedOutputStream bos;

    public Output(OutputStream out) throws IOException {
        this.os = new PrintWriter(out, true);
        this.bos = new BufferedOutputStream(out);
    }

    public void sendPlayer(String response, String path) {
        System.out.println("start");
        os.println("HTTP/1.1 200");
        os.println("Server: LocalHost Server");
        os.println("Date: " + new Date());
        os.println("Content-Type: text/html");
        os.println();
        os.flush();
        System.out.println("fin");
    }
}
