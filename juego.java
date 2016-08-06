/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import static Formulario.generarNumeros.valorTirar;
import claseConectar.conectar;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Emilio
 */
public class juego extends javax.swing.JFrame {

    /**
     * Creates new form juego
     */
    public static int contadorrespuestascorrectas;
    public static int contadorrespuestasincorrectas;
    protected static int niveldeusuario;
    public juego() {
        initComponents();
        principal();
        
    }

    void principal(){
        String usuario = IniciodeSesion.usuario.getText();
        String nivel = "SELECT * FROM nivel WHERE usuarios_usuario ='"+usuario+"'";
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(nivel);
            while(rs.next()){
                niveldeusuario = rs.getInt(2);
                System.out.println("El nivel de usuario es: "+niveldeusuario);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en conexión "+ e);
        }
        int dado = dados.num1;
        System.out.println("Este es el dado random agarrado "+dado);
        String pre = "";
        String pregunt = "SELECT * FROM preguntasyrespuestas ORDER BY rand()";
        int bandera = 0;
        try {
            Statement st1 = cn.createStatement();
            ResultSet rs1 = st1.executeQuery(pregunt);
            while(rs1.next()){
                bandera = rs1.getInt(4);
                pregunta.setLineWrap(true);
                pregunta.setWrapStyleWord(true);
                if(dado == bandera){
                    pregunta.setText(rs1.getString("preguntas"));
                    pre = rs1.getString("preguntas");
                    System.out.println(pre);  
                }  
                    
            }
            
            if(niveldeusuario >= 6 && niveldeusuario <= 10){
                cor7.setVisible(false);
                cor6.setVisible(false);
            }
            if(niveldeusuario >= 11 && niveldeusuario <= 15){
                cor7.setVisible(false);
                cor6.setVisible(false);
                cor5.setVisible(false);
            }
            if(niveldeusuario >= 11 && niveldeusuario <= 15){
                cor7.setVisible(false);
                cor6.setVisible(false);
                cor5.setVisible(false);
            }
            if(niveldeusuario >= 16 && niveldeusuario <= 20){
                cor7.setVisible(false);
                cor6.setVisible(false);
                cor5.setVisible(false);
                cor4.setVisible(false);
            
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de conexión "+ e);
        }
    }
    
    void verdadero(String respues){
        int verdaderos = 1;
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        String pregunt = "SELECT * FROM preguntasyrespuestas WHERE preguntas ='"+respues+"'";
        int verda = 0;
        try {
            Statement st1 = cn.createStatement();
            ResultSet rs1 = st1.executeQuery(pregunt);
            while(rs1.next()){
                verda = rs1.getInt(3);
            }
            
//********************************************************************************************************************************            
            if(niveldeusuario >= 1 && niveldeusuario <= 5){
                System.out.println("El nivel es: "+niveldeusuario);
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        //System.out.println("usuario "+ usuario +" Nivel "+ niveldeusuario);
                        niveldeusuario++;
                        //System.out.println("El nivel es: " + niveldeusuario);
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(SQLException | HeadlessException e){

                        }

                    }else{
                        principal();
                    }              
                }//Fin del if para comparar si la respuesta es correcta o no.
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor7.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor6.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor5.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 4){
                        cor4.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 5){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 6){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 7){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entre 1 y 5
            
//********************************************************************************************************************************
           if(niveldeusuario >= 6 && niveldeusuario <= 10){
               System.out.println("El nivel es: "+niveldeusuario);
                
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        niveldeusuario++;
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();  
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(SQLException | HeadlessException e){

                        }
                        
                    } else{
                        principal();
                    }     
                }//Fin del if comparación de respuestas correctas
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor5.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor4.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }else if(contadorrespuestasincorrectas == 4){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }else if(contadorrespuestasincorrectas == 5){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entr 6 y 10
//********************************************************************************************************************************
            if(niveldeusuario >= 11 && niveldeusuario <= 15){
                System.out.println("El nivel es: "+niveldeusuario);
                cor7.setVisible(false);
                cor6.setVisible(false);
                cor5.setVisible(false);
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        niveldeusuario++;
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();  
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(SQLException | HeadlessException e){

                        }
                        
                    } else{
                        principal();
                    }     
                }//Fin del if comparación de respuestas correctas
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor4.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }else if(contadorrespuestasincorrectas == 4){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entr 11 y 15
//********************************************************************************************************************************
            if(niveldeusuario >= 16 && niveldeusuario <= 20){
                System.out.println("El nivel es: "+niveldeusuario);
                cor7.setVisible(false);
                cor6.setVisible(false);
                cor5.setVisible(false);
                cor4.setVisible(false);
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        niveldeusuario++;
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();  
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(Exception e){

                        }
                        
                    } else{
                        principal();
                    }     
                }//Fin del if comparación de respuestas correctas
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/3");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/3");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/3");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entr 16 y 20
//********************************************************************************************************************************
        } catch (Exception e) {
        }
        
    }
    
    
    
    
    void falso(String res){
        int verdaderos = 0;
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        String pregunt = "SELECT * FROM preguntasyrespuestas WHERE preguntas ='"+res+"'";
        int verda = 0;
        try {
            Statement st1 = cn.createStatement();
            ResultSet rs1 = st1.executeQuery(pregunt);
            while(rs1.next()){
                verda = rs1.getInt(3);
            } 
//********************************************************************************************************************************            
            if(niveldeusuario >= 1 && niveldeusuario <= 6){
                System.out.println("El nivel es: "+niveldeusuario);
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        niveldeusuario++;
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();  
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(Exception e){

                        }

                    }else{
                        principal();
                    }              
                }//Fin del if para comparar si la respuesta es correcta o no.
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor7.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor6.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor5.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 4){
                        cor4.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 5){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 6){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }else if(contadorrespuestasincorrectas == 7){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/7");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entre 1 y 5
            
//********************************************************************************************************************************
           if(niveldeusuario >= 6 && niveldeusuario <= 10){
               System.out.println("El nivel es: "+niveldeusuario);
                cor7.setVisible(false);
                cor6.setVisible(false);
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        niveldeusuario++;
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();     
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(Exception e){

                        }
                        
                    } else{
                        principal();
                    }     
                }//Fin del if comparación de respuestas correctas
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor5.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor4.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }else if(contadorrespuestasincorrectas == 4){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }else if(contadorrespuestasincorrectas == 5){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/5");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entr 6 y 10
//********************************************************************************************************************************
            if(niveldeusuario >= 11 && niveldeusuario <= 15){
                System.out.println("El nivel es: "+niveldeusuario);
                cor7.setVisible(false);
                cor6.setVisible(false);
                cor5.setVisible(false);
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        niveldeusuario++;
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();    
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(Exception e){

                        }
                        
                    } else{
                        principal();
                    }     
                }//Fin del if comparación de respuestas correctas
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor4.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }else if(contadorrespuestasincorrectas == 4){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/4");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entr 11 y 15
//********************************************************************************************************************************
            if(niveldeusuario >= 16 && niveldeusuario <= 20){
                System.out.println("El nivel es: "+niveldeusuario);
                cor7.setVisible(false);
                cor6.setVisible(false);
                cor5.setVisible(false);
                cor4.setVisible(false);
                if(verdaderos == verda){
                    contadorrespuestascorrectas++;
                    JOptionPane.showMessageDialog(null, "Respuesta correcta "+contadorrespuestascorrectas+"/20");
                    if(contadorrespuestascorrectas == 20){
                        JOptionPane.showMessageDialog(null, "Felicidades!!! Completaste este nivel");
                        String usuario = IniciodeSesion.usuario.getText();
                        niveldeusuario++;
                        String sql="UPDATE nivel SET nivelusuario ='"+niveldeusuario+"' WHERE usuarios_usuario ='"+usuario+"'";
                        try {
                            PreparedStatement pst = cn.prepareStatement(sql);
                            pst.executeUpdate();   
                            JOptionPane.showMessageDialog(null, "Felicidades, pasaste al nivel "+ niveldeusuario);
                            contadorrespuestascorrectas = 0;
                            contadorrespuestasincorrectas = 0;
                            this.dispose();
                            dados volver = new dados();
                            volver.setVisible(true);
                            volver.pack();
                        } catch(Exception e){

                        }
                        
                    } else{
                        principal();
                    }     
                }//Fin del if comparación de respuestas correctas
                else{
                    contadorrespuestasincorrectas++;
                    if(contadorrespuestasincorrectas == 1){
                        cor3.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/3");
                        principal();
                    }
                    if(contadorrespuestasincorrectas == 2){
                        cor2.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/3");
                        principal();
                    }else if(contadorrespuestasincorrectas == 3){
                        cor1.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Respuesta incorrecta "+contadorrespuestasincorrectas+"/3");
                        principal();
                    }if(cor1.isEnabled()== false){
                        JOptionPane.showMessageDialog(null, "PERDISTE!!!!");
                        contadorrespuestascorrectas = 0;
                        contadorrespuestasincorrectas = 0;
                        dados volver = new dados();
                        volver.setVisible(true);
                        volver.pack();
                        this.dispose();
                    }
                }
            }//Fin del if si el nivel esta entr 16 y 20
//********************************************************************************************************************************
        } catch (Exception e) {
        }
    }
    
    
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cor1 = new javax.swing.JLabel();
        cor2 = new javax.swing.JLabel();
        cor3 = new javax.swing.JLabel();
        cor4 = new javax.swing.JLabel();
        cor5 = new javax.swing.JLabel();
        cor6 = new javax.swing.JLabel();
        cor7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pregunta = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(211, 208, 102));

        cor1.setText("jLabel1");

        cor2.setText("jLabel1");

        cor3.setText("jLabel1");

        cor4.setText("jLabel1");

        cor5.setText("jLabel1");

        cor6.setText("jLabel1");

        cor7.setText("jLabel1");

        pregunta.setEditable(false);
        pregunta.setBackground(new java.awt.Color(240, 240, 240));
        pregunta.setColumns(20);
        pregunta.setFont(new java.awt.Font("Verdana", 2, 16)); // NOI18N
        pregunta.setRows(5);
        pregunta.setAutoscrolls(false);
        pregunta.setBorder(null);
        jScrollPane1.setViewportView(pregunta);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("VERDADERO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("FALSO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(36, 36, 36))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cor1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cor2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cor3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cor4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cor5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cor6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cor7))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cor1)
                    .addComponent(cor2)
                    .addComponent(cor3)
                    .addComponent(cor4)
                    .addComponent(cor5)
                    .addComponent(cor6)
                    .addComponent(cor7))
                .addGap(53, 53, 53)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nose = juego.pregunta.getText();
        verdadero(nose);
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nose = juego.pregunta.getText();
        falso(nose);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new juego().setVisible(true);
            }
        });
    }
    
    conectar cc = new conectar();
    Connection cn = cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cor1;
    private javax.swing.JLabel cor2;
    private javax.swing.JLabel cor3;
    private javax.swing.JLabel cor4;
    private javax.swing.JLabel cor5;
    private javax.swing.JLabel cor6;
    private javax.swing.JLabel cor7;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea pregunta;
    // End of variables declaration//GEN-END:variables
}
