/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillermo Veintemilla
 * Clase Palabra donde trabajamos con un fichero donde se almacenan las plabras del juego
 */
public class Palabra {
    
    ArrayList<String> palabras = new ArrayList<String>();
    
    /**
     * Constructor de Palabra
     */
    public Palabra() {
    }
    
    /**
     * Metodo que elige la palabra del juego
     * @return palabra del juego
     */
    public String elegirPalabra(){
        String palabraElegida="";
        try {
            this.palabras = leerPalabras();
            int numeroPalabra = (int) Math.round(Math.random() * palabras.size());
            if (numeroPalabra == palabras.size()) {
                numeroPalabra--;
            }
            palabraElegida = palabras.get(numeroPalabra);
            return palabraElegida;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Palabra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return palabraElegida;
    }
    
    /**
     * Metodo que lee el fichero para coger todas la palabras que hay en el
     * @return array de palabras
     * @throws ClassNotFoundException 
     */
    public static ArrayList leerPalabras() throws ClassNotFoundException {
        Scanner f;
        ArrayList<String> palabras = new ArrayList<String>();

        try {
            f = new Scanner(new File("palabras.txt"));

            while (f.hasNext()) {
                String palabra = f.next();
                palabras.add(palabra);
            }

            f.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error de apertura");
        } catch (IOException ioe) {
            System.out.println("Error de entrada/salida");
        }
        return palabras;
    }
}
