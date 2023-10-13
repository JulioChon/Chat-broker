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
import static org.itson.chatbroker.Broker.Broker.puertoServidor;
import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Dominio.PaqueteDatos;

/**
 *
 * @author Gabriel Mancinas
 */
public class BrokerServidor implements Runnable {
           static Socket servidorSocket;
    static Socket socketRemitente = new Socket();

    public static Socket getServidorSocket() {
        return servidorSocket;
    }

    public static void setServidorSocket(Socket servidorSocket) {
        BrokerServidor.servidorSocket = servidorSocket;
    }

    public Socket getSocketRemitente() {
        return socketRemitente;
    }

    public void setSocketRemitente(Socket socketRemitente) {
        this.socketRemitente = socketRemitente;
    }
    
    public BrokerServidor() {
        servidorSocket = new Socket();
    }

    public  void iniciarSocketServidor() {
        try {
            ServerSocket socketServer = new ServerSocket(puertoServidor);
            servidorSocket = socketServer.accept();
           // String direccionServidor = servidorSocket.getInetAddress().getHostAddress();
            //Broker.direccionesServidores.add(direccionServidor);
            Broker.direccionesServerSocket.add(servidorSocket);
            while (true) {
                this.enviarInformacionCliente(servidorSocket);
            }
            //socketServidor.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  void enviarInformacionCliente(Socket socketServidor) {
        try {
            PaqueteDatos paqueteDatosRecibido;
            ObjectInputStream paqueteDatos = new ObjectInputStream(socketServidor.getInputStream());
            paqueteDatosRecibido = (PaqueteDatos) paqueteDatos.readObject();
            if (paqueteDatosRecibido.getPara() == Mensaje.CLIENTE) {
                //String ipRemitente = paqueteDatosRecibido.getIp();
                for (int i = 0; i < Broker.direccionesClienteSocket.size(); i++) {
                    //System.out.println(Broker.direccionesClienteSocket);
                    //String ipDestinatario = Broker.direccionesClienteSocket.get(i).getInetAddress().getHostAddress();
                    System.out.println(socketRemitente);
                    System.out.println(Broker.direccionesClienteSocket.get(i));
                    if (!socketRemitente.equals(Broker.direccionesClienteSocket.get(i))) {
                        Socket socketEnviarCliente = Broker.direccionesClienteSocket.get(i);
                        ObjectOutputStream paqueteDatosEnvio = new ObjectOutputStream(socketEnviarCliente.getOutputStream());
                        paqueteDatosEnvio.writeObject(paqueteDatosRecibido);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void run() {
        iniciarSocketServidor();
    }

}
