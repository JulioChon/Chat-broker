/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.chatbroker.Broker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import static org.itson.chatbroker.Broker.Broker.direccionesServidores;
import static org.itson.chatbroker.Broker.Broker.puertoServidor;
import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Dominio.PaqueteDatos;

/**
 *
 * @author Gabriel Mancinas
 */
public class BrokerServidor implements Runnable {
           static Socket servidorSocket;
    
    
    public BrokerServidor() {
        servidorSocket = new Socket();
    }

    public static void iniciarSocketServidor() {
        try {
            ServerSocket socketServer = new ServerSocket(puertoServidor);
            servidorSocket = socketServer.accept();
            String direccionServidor = servidorSocket.getInetAddress().getHostAddress();
            Broker.direccionesServidores.add(direccionServidor);
            Broker.direccionesServerSocket.add(servidorSocket);
            while (true) {
                enviarInformacionCliente(servidorSocket);
            }
            //socketServidor.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void enviarInformacionCliente(Socket socketServidor) {
        try {
            PaqueteDatos paqueteDatosRecibido;
            ObjectInputStream paqueteDatos = new ObjectInputStream(socketServidor.getInputStream());
            paqueteDatosRecibido = (PaqueteDatos) paqueteDatos.readObject();
            if (paqueteDatosRecibido.getPara() == Mensaje.CLIENTE) {
                String ipRemitente = paqueteDatosRecibido.getIp();
                for (int i = 0; i < Broker.direccionesClientes.size(); i++) {
                    String ipDestinatario = Broker.direccionesClienteSocket.get(i).getInetAddress().getHostAddress();
                    if (!ipRemitente.equalsIgnoreCase(ipDestinatario)) {
                        Socket socketEnviarCliente = Broker.direccionesClienteSocket.get(i);
                        ObjectOutputStream paqueteDatosEnvio = new ObjectOutputStream(socketEnviarCliente.getOutputStream());
                        paqueteDatosEnvio.writeObject(paqueteDatosRecibido);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void run() {
        System.out.println("Run broker server");
        iniciarSocketServidor();
    }

}
