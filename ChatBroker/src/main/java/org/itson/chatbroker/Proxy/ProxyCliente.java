/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.chatbroker.Proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Dominio.PaqueteDatos;

/**
 *
 * @author Gabriel Mancinas
 */
public class ProxyCliente implements Runnable{
    PaqueteDatos paqueteEnvioDatos;
    PaqueteDatos paqueteReciboDatos;
    int puerto = 9091;
    Socket clienteSocket;
    
    public ProxyCliente(){
        
    }
    public void empaquetarParametros(String nombre, String mensaje,Mensaje tipo){
        paqueteEnvioDatos = new PaqueteDatos(nombre,mensaje,"localhost",tipo);
    }
    public void iniciarSocket(){
        try {
            clienteSocket= new Socket("localhost",puerto);
            System.out.println(clienteSocket==null);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    public void enviarDatos(){
        try {
           // System.out.println(clienteSocket==null);
            ObjectOutputStream paqueteDatos = new ObjectOutputStream(clienteSocket.getOutputStream());
            paqueteDatos.writeObject(paqueteEnvioDatos);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    public void cerrarSocket(){
        try {
            clienteSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void recibirDatos(){
        while(true){
         try{
            ObjectInputStream paqueteDatos = new ObjectInputStream(clienteSocket.getInputStream());
            paqueteReciboDatos = (PaqueteDatos) paqueteDatos.readObject();
            desempaquetarDatos();   
        }catch(Exception ex){
           // ex.printStackTrace();
            //System.out.println(ex.getMessage());
        }   
        }
        
            }
    public PaqueteDatos getPaqueteEnvioDatos() {
        return paqueteEnvioDatos;
    }

    public void setPaqueteEnvioDatos(PaqueteDatos paqueteEnvioDatos) {
        this.paqueteEnvioDatos = paqueteEnvioDatos;
    }

    public PaqueteDatos getPaqueteReciboDatos() {
        return paqueteReciboDatos;
    }

    public void setPaqueteReciboDatos(PaqueteDatos paqueteReciboDatos) {
        this.paqueteReciboDatos = paqueteReciboDatos;
    }
    
    public void desempaquetarDatos(){
        String nombre, mensaje, ip;
        nombre = paqueteReciboDatos.getNombre();
        mensaje = paqueteReciboDatos.getMensaje();
        ip = paqueteReciboDatos.getIp();
        System.out.println(nombre+": "+mensaje+", de la IP: "+ip);
    }

    @Override
    public void run() {
        recibirDatos();
    }
    
   
    
   
    
}
