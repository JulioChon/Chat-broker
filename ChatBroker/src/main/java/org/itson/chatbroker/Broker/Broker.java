/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.chatbroker.Broker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.chatbroker.Dominio.PaqueteDatos;

/**
 *
 * @author Gabriel Mancinas 00000233410
 */
public class Broker{
static int puertoServidor = 9090;
static int puertoCliente = 9091;
static List<String> direccionesClientes = new ArrayList();
static List<String> direccionesServidores = new ArrayList();
static List<Socket> direccionesServerSocket = new ArrayList();
static List<Socket> direccionesClienteSocket = new ArrayList();
static Thread hiloCliente;
static Thread hiloServidor;
static BrokerCliente brokerCliente = new BrokerCliente(); 
static BrokerServidor brokerServidor = new BrokerServidor();

    public static void main(String[] args) {
    hiloCliente = new Thread(brokerCliente);
    hiloServidor = new Thread(brokerServidor);
    hiloCliente.start();
    hiloServidor.start();
    }
    
}
