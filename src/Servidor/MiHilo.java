/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;
import Cliente.Jugador;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillermo Veintemilla
 * Clase MiHilo que lanza un hilo para cada cliente que se quiere conectar al servidor
 */
public class MiHilo extends Thread {

    Socket conexion;
    MiServidor servidor;
    String palabraElegida;
    InterfazServidor is;
    DataOutputStream output;
    DataInputStream input;

    /**
     * Constructor de MiHilo
     * @param connection
     * @param server
     * @param palabra
     * @param is 
     */
    public MiHilo(Socket connection, MiServidor server, String palabra, InterfazServidor is) {
        this.conexion = connection;
        this.servidor = server;
        this.palabraElegida = palabra;
        this.is = is;
        try {
            input = new DataInputStream(conexion.getInputStream());
            output = new DataOutputStream(conexion.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(MiHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo run que implenta la accion de jugar
     */
    public void run() {
        String nombre;
        int rondas, errores;
        boolean acabado, todosAcabado = false;
        try {
            int tipoAccion = 0;
            tipoAccion = input.readInt();
            switch (tipoAccion) {
                case 0://Añade nuevo jugador
                    output.writeBoolean(is.isComenzar());
                    nombre = input.readUTF();
                    is.getjTextArea1().append("Conectado " + nombre + " recibido por " + conexion.getInetAddress().getHostName() + "\n");
                    output.writeUTF(palabraElegida);
                    Jugador j = new Jugador(nombre, 0, 0);
                    is.getJugadores().add(j);
                    while (!is.isComenzar()) {
                        System.out.println("");
                    }
                    output.writeInt(99);
                    break;
                case 1://pulsa boton
                    nombre = input.readUTF();
                    rondas = input.readInt();
                    errores = input.readInt();
                    for (int i = 0; i < is.getJugadores().size(); i++) {
                        if (is.getJugadores().get(i).getNombre().equalsIgnoreCase(nombre)) {
                            is.getJugadores().get(i).setErrores(errores);
                            is.getJugadores().get(i).setRondas(rondas);
                        }
                    }
                    acabado = input.readBoolean();
                    if (acabado) {
                        for (int i = 0; i < is.getJugadores().size(); i++) {
                            if (is.getJugadores().get(i).getNombre().equalsIgnoreCase(nombre)) {
                                is.getJugadores().get(i).setAcabado(acabado);
                            }
                        }
                        do {
                            todosAcabado = true;
                            for (int i = 0; i < is.getJugadores().size(); i++) {
                                if (is.getJugadores().get(i).isAcabado() == false) {
                                    todosAcabado = false;
                                }
                            }
                        } while (!todosAcabado);
                        output.writeInt(1);
                        ArrayList<Jugador> posiblesGanadores = new ArrayList<Jugador>();
                        for (int indice = 0; indice < is.getJugadores().size(); indice++) {
                            if (is.getJugadores().get(indice).getErrores() != 5) {
                                posiblesGanadores.add(is.getJugadores().get(indice));
                            }
                        }
                        if (posiblesGanadores.size() != 0) {
                            Jugador jugadorGanador = new Jugador();
                            jugadorGanador = posiblesGanadores.get(0);
                            for (int indice = 0; indice < posiblesGanadores.size(); indice++) {
                                if(posiblesGanadores.get(indice).getRondas()<jugadorGanador.getRondas()){
                                    jugadorGanador=posiblesGanadores.get(indice);
                                }
                            }
                            output.writeUTF(jugadorGanador.getNombre());
                            is.getLabelGanador().setText(jugadorGanador.getNombre());
                        } else {
                            output.writeUTF("");
                        }
                        

                        input.close();
                        output.close();
                    }
                    break;
            }

        } catch (IOException ex) {
            Logger.getLogger(MiHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
