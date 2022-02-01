package com.juan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main  {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        ServerSocket server = new ServerSocket(8000);
        System.out.println("servidor escuchando");
        ChatMensaje chat = new ChatMensaje();

        while(true) {
            socket = server.accept();
            HiloServer hiloServer = new HiloServer(socket , chat);
            hiloServer.start();


        }


    }


}
