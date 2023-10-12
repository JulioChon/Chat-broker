/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.chatbroker.Servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.chatbroker.Dominio.PaqueteDatos;
import org.itson.chatbroker.Proxy.ProxyServidor;

/**
 *
 * @author Gabriel Mancinas
 */
public class ChatServer{

    /**
     * @param args the command line arguments
     */
    final static int puerto = 9090;
    static ProxyServidor proxyServidor;
    static List<PaqueteDatos> historialChat = new ArrayList();
    //final static Thread hilo = new Thread(new ChatServer());
   // static PaqueteDatos paqueteDatosRecibido;
    //static List<ObjectOutputStream> outputStreams = new ArrayList<>();
    
    public static void main(String[] args) {
    iniciarServidor();
    }
    
    public static void iniciarServidor(){
        try{
         proxyServidor = new ProxyServidor();
         proxyServidor.iniciarSocket();
         proxyServidor.run();
         
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void actualizarHistorial(PaqueteDatos paqueteDatos){
        historialChat.add(paqueteDatos);
        proxyServidor.enviarDatos(paqueteDatos);
    }
    


  
    }
    

