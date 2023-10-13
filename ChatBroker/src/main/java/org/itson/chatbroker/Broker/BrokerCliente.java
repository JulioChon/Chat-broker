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
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.itson.chatbroker.Broker.Broker.puertoCliente;
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
                //String direccionCliente;
                socketCliente = socketBroker.accept();
                //direccionCliente = socketCliente.getInetAddress().getHostAddress();
                    //Broker.direccionesClientes.add(direccionCliente);
                    Broker.direccionesClienteSocket.add(socketCliente);
                    Thread hilo = new Thread(new enviarInformacionCliente(socketCliente));
                    hilo.start();
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

        public void enviarInformacionServidor(Socket socketCliente) throws IOException {
            try {
                while (true) {
                    BrokerServidor.socketRemitente = socketCliente;
                    PaqueteDatos paqueteDatosRecibido;
                    ObjectInputStream paqueteDatos = new ObjectInputStream(socketCliente.getInputStream());
                    Socket socketEnviarServidor = Broker.direccionesServerSocket.get(0);
                    paqueteDatosRecibido = (PaqueteDatos) paqueteDatos.readObject();
                    ObjectOutputStream paqueteDatosEnvio = new ObjectOutputStream(socketEnviarServidor.getOutputStream());
                    paqueteDatosEnvio.writeObject(paqueteDatosRecibido);
                }
            } catch (IOException | ClassNotFoundException ex) {

                this.eliminarConexion();
            }
        }

        public void eliminarConexion() {
            try {
                Broker.direccionesClienteSocket.remove(socketCliente);
                this.socketCliente.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar la conexion");
            }
        }

        @Override
        public void run() {

            try {
                enviarInformacionServidor(socketCliente);
            } catch (IOException ex) {
                Logger.getLogger(BrokerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
