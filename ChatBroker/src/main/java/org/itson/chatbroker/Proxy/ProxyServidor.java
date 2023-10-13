/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.chatbroker.Proxy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Dominio.PaqueteDatos;
import org.itson.chatbroker.Servidor.ChatServer;

/**
 *
 * @author Gabriel Mancinas
 */
public class ProxyServidor implements Runnable{
PaqueteDatos paqueteEnvioDatos;
PaqueteDatos paqueteReciboDatos;
int puerto = 9090;    
Socket servidorSocket;
final String ip = "localhost";
    
    public ProxyServidor(){   
    }
    public void empaquetarParametros(String nombre, String mensaje, String ip){
        paqueteEnvioDatos = new PaqueteDatos(nombre,mensaje,ip);
    }
    public void iniciarSocket(){
    try {
        servidorSocket = new Socket(ip, puerto);
        PaqueteDatos paquete = new PaqueteDatos(Mensaje.SERVER);
        ObjectOutputStream paqueteDatos = new ObjectOutputStream(servidorSocket.getOutputStream());
        paqueteDatos.writeObject(paquete);
    } catch (IOException ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
    }
    }
    public void enviarDatos(PaqueteDatos paqueteReciboDatos){
        try {
            ObjectOutputStream paqueteDatos = new ObjectOutputStream(servidorSocket.getOutputStream());
            paqueteDatos.writeObject(paqueteReciboDatos);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    public void recibirDatos(){
        try{
            while(true){
            ObjectInputStream paqueteDatos = new ObjectInputStream(servidorSocket.getInputStream());
            paqueteReciboDatos = (PaqueteDatos) paqueteDatos.readObject();
            desempaquetarDatos();
            ChatServer.actualizarHistorial(paqueteReciboDatos);            
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
    
    public void cerrarSocket(){
        try {
            servidorSocket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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

