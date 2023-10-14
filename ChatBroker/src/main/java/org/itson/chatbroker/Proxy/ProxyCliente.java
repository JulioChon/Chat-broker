/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.chatbroker.Proxy;

import org.itson.chatbroker.Conexiones.ConexionCliente;
import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Dominio.PaqueteDatos;
import org.itson.chatbroker.Interfaces.IProxyCliente;

/**
 *
 * @author Gabriel Mancinas
 */
public class ProxyCliente implements IProxyCliente{
    private ConexionCliente conexionCliente = new ConexionCliente();
    @Override
    public void empaquetarParametros(String nombre, String mensaje, Mensaje tipo) {
        conexionCliente.empaquetarParametros(nombre, mensaje, tipo);
    }

    @Override
    public void iniciarSocket() {
        conexionCliente.iniciarSocket();
    }

    @Override
    public void cerrarSocket() {
        conexionCliente.cerrarSocket();
    }
    
    @Override
    public void recibirDatos() {
        conexionCliente.recibirDatos();
    }
        @Override
    public void enviarDatos() {
        conexionCliente.enviarDatos();
    }
    @Override
    public void desempaquetarDatos() {
        conexionCliente.desempaquetarDatos();
    }
    
    @Override
    public void iniciarHilo(){
        conexionCliente.iniciarHilo();
    }


    
}
