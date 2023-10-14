/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.chatbroker.Proxy;

import org.itson.chatbroker.Conexiones.ConexionServidor;
import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Dominio.PaqueteDatos;
import org.itson.chatbroker.Interfaces.IProxyCliente;
import org.itson.chatbroker.Interfaces.IProxyServidor;

/**
 *
 * @author Gabriel Mancinas
 */
public class ProxyServidor implements IProxyServidor{
    private ConexionServidor conexionServidor = new ConexionServidor();
    
    @Override
    public void empaquetarParametros(String nombre, String mensaje, String ip) {
        conexionServidor.empaquetarParametros(nombre, mensaje, ip);
    }

    @Override
    public void iniciarSocket() {
        conexionServidor.iniciarSocket();
    }

    @Override
    public void cerrarSocket() {
        conexionServidor.cerrarSocket();
    }

    @Override
    public void recibirDatos() {
        conexionServidor.cerrarSocket();
    }
    
    @Override
    public void enviarDatos() {
        conexionServidor.enviarDatos();
    }
    @Override
    public void desempaquetarDatos() {
    conexionServidor.desempaquetarDatos();
    }

    @Override
    public void iniciarHilo() {
        conexionServidor.iniciarHilo();
    }

   
}
