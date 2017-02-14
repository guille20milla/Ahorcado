/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Guillermo Veintemilla
 * Clase Servidor donde creamos el servidor
 */
public class MiServidor {

    private ServerSocket miServidor;
    private Socket connection;

    /**
     * Metodo que inicia el servidor
     */
    public void conectar() {
        try {
            miServidor = new ServerSocket(4332);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * @return the miServidor
     */
    public ServerSocket getMiServidor() {
        return miServidor;
    }

    /**
     * @return the connection
     */
    public Socket getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Socket connection) {
        this.connection = connection;
    }

}
