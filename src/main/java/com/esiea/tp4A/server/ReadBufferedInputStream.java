package com.esiea.tp4A.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadBufferedInputStream extends BufferedInputStream {
    private String requestHeader;
    public ReadBufferedInputStream(InputStream in) throws IOException {
        super(in);
        this.requestHeader();
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
        }
        this.requestHeader = requestHeader;
        return this.requestHeader;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public String getMethod(){
        return this.requestHeader.split("\n")[0].split(" ")[0];
    }

    public String getPath(){
        return this.requestHeader.split("\n")[0].split(" ")[1];
    }


}
