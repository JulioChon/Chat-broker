/*
Clase ChatBroker.java creada el 08/10/2023.
*/
package org.itson.chatbroker;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.chatbroker.Dominio.Cliente;
import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Proxy.ProxyCliente;

/**
 *
 * @author Gabriel Mancinas
 */
public class ChatBroker implements Runnable{
static Scanner tec = new Scanner(System.in);
static String nombre,mensaje,ip;
static Random random = new Random();
 //final static Thread hilo = new Thread(new ChatBroker());
static ProxyCliente proxyCliente = new ProxyCliente();


    public static void main(String[] args) {
      Thread chat = new Thread(new ChatBroker());
      proxyCliente.iniciarSocket();
      chat.start();
      Thread proxyClienteHilo = new Thread(proxyCliente);
      proxyClienteHilo.start();
    }

    @Override
    public void run() {
        System.out.println("--Chat a través de consola utilizando la arquitectura Broker--"); 
        System.out.println("Ingresa tu nombre para poder chatear: ");
        nombre = tec.nextLine();
        System.out.println("Ingresa el mensaje que quieres enviar: ");
        do{
        mensaje = tec.nextLine();
        if(mensaje.equalsIgnoreCase("desconectar")){
            break;
        }
        proxyCliente.empaquetarParametros(nombre,mensaje,Mensaje.CLIENTE);
        proxyCliente.enviarDatos(); 
        }while(true);
        proxyCliente.cerrarSocket();
    }
    
}
