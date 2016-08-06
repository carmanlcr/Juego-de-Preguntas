/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author LSMORALES
 */
public class generarNumeros {
    
    //--------------------------------------------------------------------------------------------//
    
    public static int valorTirar;
    public int calcularNumeros(){ //Función para generar los lados del dado de forma aleatoria
        Random Generar = new Random();
        valorTirar = Generar.nextInt(6)+1;
        return valorTirar; 
    }
    
    //--------------------------------------------------------------------------------------------//
    
    public ImageIcon icoImagen;
    public ImageIcon gifDado1(int dado1){ //Función para hacer el movimiento del dado dependiendo del numero

        switch(dado1){
            case 1:
                icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\gifs\\dados-01.gif");
                
            case 2:
                icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\gifs\\dados-02.gif");
            
            case 3:
                icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\gifs\\dados-03.gif");
            
            case 4:
                icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\gifs\\dados-04.gif");
            
            case 5:
                icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\gifs\\dados-05.gif");
                    
            case 6:
                icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\gifs\\dados-06.gif");
        }
        return icoImagen;
    }
    
    //--------------------------------------------------------------------------------------------//
    
    //--------------------------------------------------------------------------------------------//
    public ImageIcon Imaen(int numero){ //Función para imprimir el numero al detener el lanzamiento
        switch(numero){

           case 1:
               icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\1.png");
               break;
                
           case 2:
               icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\2.png");
               break;
                    
           case 3:
               icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\3.png");
               break;
                    
           case 4:
               icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\4.png");
               break;
                    
           case 5:
               icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\5.png");
               break;
                    
           case 6:
               icoImagen = new ImageIcon("C:\\Users\\LSMORALES\\Documents\\NetBeansProjects\\JuegodePreguntas\\Imagenes Dado\\6.png");
               break;
        }
        return icoImagen;
    }
    //--------------------------------------------------------------------------------------------//
}
