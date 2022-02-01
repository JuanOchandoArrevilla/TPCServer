package com.juan;

import java.io.*;

import java.net.Socket;
import java.time.LocalDateTime;

public class HiloServer extends Thread {
    private ChatMensaje chat = null;
    private Socket socket = null;

      private DataInputStream in = null ;
      private DataOutputStream out = null;

    public HiloServer(Socket socket, ChatMensaje sicronizada) {
        this.socket = socket;
        this.chat = sicronizada;
    }

    public void run() {
        System.out.println("conexion recibida desde " + socket.getInetAddress());


        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


            String usuario = in.readUTF();
            String time = in.readUTF();
            System.out.println("se ha enviado mensaje");

            out.writeUTF( usuario );
            out.writeUTF(" hora local " + time);
            String mensaje = "";


            while (!mensaje.equals("bye")) {
                mensaje = in.readUTF();
                String comprobarMensaje = "";
                if (mensaje.length() > 8) {
                   comprobarMensaje = mensaje.substring(0,8);
                   if (mensaje.contains(" ")) {
                       out.writeUTF(usuario + mensaje);
                   }
                }
                if (comprobarMensaje.equals("message:")) {
                    String mensajeValido = mensaje.substring(8);
                    LocalDateTime locaDate = LocalDateTime.now();
                    int hours  = locaDate.getHour();
                    int minutes = locaDate.getMinute();
                    int seconds = locaDate.getSecond();
                    String time2 = hours  + ":" + minutes +":" + seconds;


                    String mensajeCompleto = usuario + "<" + time2 + "> " +  mensajeValido;
                    chat.agregarMensaje(mensajeCompleto);
                    
                    out.writeUTF(chat.obtenerMensaje());
                } else if (mensaje.equals("bye")) {
                    out.writeUTF("good bye");
                } else {
                    out.writeUTF("error");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
                System.out.println("nnñoo se acabo lo que se daba");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}