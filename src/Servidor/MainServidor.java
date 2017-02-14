/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Servidor.Palabra;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillermo Veintemilla
 * Clase Main del servidor
 */
public class MainServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InterfazServidor i = new InterfazServidor();
        MiServidor servidor = new MiServidor();
        Palabra p = new Palabra();
        String elegida = p.elegirPalabra();
        servidor.conectar();
        while (true) {
            try {
                servidor.setConnection(servidor.getMiServidor().accept());
                MiHilo hilo = new MiHilo(servidor.getConnection(),servidor,elegida,i);
                hilo.start();
            } catch (IOException ex) {
                Logger.getLogger(MainServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
