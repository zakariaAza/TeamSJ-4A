package com.esiea.tp4A.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadBufferedInputStream extends BufferedInputStream {
    public ReadBufferedInputStream(InputStream in) {
        super(in);
    }
    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder(); int b;
        while ((b = read()) >= 0) {
            if (b == '\n') break;
            if (b != '\r') sb.append((char) b);
        }
        if (b == -1 && sb.length() == 0) return null;
        return sb.toString();
    }

    public String requestHeader() throws IOException {
        String line = "."; String requestHeader = "";
        while(!line.equals("")) {
            line = readLine();
            if(line != null) requestHeader += line + "\n";
        }return requestHeader;
    }

    public String getMethod(){
        try {
            return requestHeader().split("\n")[0].split(" ")[0];
        }catch (IOException e){
            System.out.println("Error getMethod");
        }return "";
    }

    public String getPath(){
        try {
            return requestHeader().split("\n")[0].split(" ")[1];
        }catch (IOException e){
            System.out.println("Error getPath");
        }return "";
    }


}
