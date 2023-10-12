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
import static org.itson.chatbroker.Broker.Broker.direccionesClientes;
import static org.itson.chatbroker.Broker.Broker.puertoCliente;
import static org.itson.chatbroker.Broker.Broker.puertoServidor;
import org.itson.chatbroker.Dominio.PaqueteDatos;

/**
 *
 * @author Gabriel Mancinas
 */
public class BrokerCliente implements Runnable {

    public void iniciarSocketCliente() {
        try {
            ServerSocket socketBroker = new ServerSocket(puertoCliente);
            while (true) {
                Socket socketCliente;
                String direccionCliente;
                System.out.println("antes");
                socketCliente = socketBroker.accept();
                System.out.println("despues del accep");
                direccionCliente = socketCliente.getInetAddress().getHostAddress();
                if (!Broker.direccionesClienteSocket.contains(socketBroker)) {
                    Broker.direccionesClientes.add(direccionCliente);
                    Broker.direccionesClienteSocket.add(socketCliente);
                    Thread hilo = new Thread(new enviarInformacionCliente(socketCliente));
                    hilo.start();
                    System.out.println("hilo");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        this.iniciarSocketCliente();
    }

    public class enviarInformacionCliente implements Runnable {

        Socket socketCliente;

        public enviarInformacionCliente(Socket socketCliente) {
            this.socketCliente = socketCliente;

        }

        public static void enviarInformacionServidor(Socket socketCliente) {
            try {
                while (true) {
                    System.out.println("otro hilo");
                    PaqueteDatos paqueteDatosRecibido;
                    ObjectInputStream paqueteDatos = new ObjectInputStream(socketCliente.getInputStream());
                    Socket socketEnviarServidor = Broker.direccionesServerSocket.get(0);
                    paqueteDatosRecibido = (PaqueteDatos) paqueteDatos.readObject();
                    ObjectOutputStream paqueteDatosEnvio = new ObjectOutputStream(socketEnviarServidor.getOutputStream());
                    paqueteDatosEnvio.writeObject(paqueteDatosRecibido);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());

            }
        }

        @Override
        public void run() {

            enviarInformacionServidor(socketCliente);
        }

    }

}
