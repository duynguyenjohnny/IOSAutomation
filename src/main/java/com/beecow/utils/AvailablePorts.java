package com.beecow.utils;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by HangPham on 12/14/2016.
 */
public class AvailablePorts {
    public String getPort() throws IOException {
        ServerSocket socket=new ServerSocket(0);
        socket.setReuseAddress(true);
        String port= String.valueOf(socket.getLocalPort());
//        String port=Integer.toString(socket.getLocalPort());
        System.out.println("available port: "+port);
        socket.close();
        return port;
    }
//    public static void main(String[] args) throws IOException {
//        AvailablePorts availablePort=new AvailablePorts();
//        String port = availablePort.getPort();
//        System.out.println("get port: "+port);
//        System.out.println("get port: "+availablePort.getPort());
//        System.out.println("get port: "+availablePort.getPort());
//    }
}

