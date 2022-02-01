package com.juan;
import java.util.ArrayList;

public class ChatMensaje {
    ArrayList<String> mensajes = new ArrayList<String>();

    public synchronized void agregarMensaje(String s) {
        mensajes.add(s);
    }

    public synchronized String obtenerMensaje() {
        String mensaje = "";
        for (String s: mensajes) {
            mensaje += "\n" + s;
        }
        return mensaje;
    }


}
