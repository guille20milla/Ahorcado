/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import javax.swing.JLabel;

/**
 *
 * @author Guillermo Veintemilla
 * Clase Jugador, que es para cada jugador que se conecta a la partida
 */
public class Jugador {
    
    private String nombre;
    private int errores;
    private int rondas;
    private boolean acabado=false;
    
    /**
     * Constructor de Jugador
     */
    public Jugador(){}
    
    /**
     * Constructor de Jugador
     * @param n
     * @param er
     * @param ro 
     */
    public Jugador(String n,int er,int ro){
        this.nombre=n;
        this.errores=er;
        this.rondas=ro;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the errores
     */
    public int getErrores() {
        return errores;
    }

    /**
     * @param errores the errores to set
     */
    public void setErrores(int errores) {
        this.errores = errores;
    }

    /**
     * @return the rondas
     */
    public int getRondas() {
        return rondas;
    }

    /**
     * @param rondas the rondas to set
     */
    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    /**
     * @return the acabado
     */
    public boolean isAcabado() {
        return acabado;
    }

    /**
     * @param acabado the acabado to set
     */
    public void setAcabado(boolean acabado) {
        this.acabado = acabado;
    }
}
