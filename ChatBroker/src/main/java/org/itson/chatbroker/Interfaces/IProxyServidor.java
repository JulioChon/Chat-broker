/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.chatbroker.Interfaces;

import org.itson.chatbroker.Dominio.Mensaje;
import org.itson.chatbroker.Dominio.PaqueteDatos;

/**
 *
 * @author Gabriel Mancinas
 */
public interface IProxyServidor {

    public void empaquetarParametros(String nombre, String mensaje, String ip);

    public void iniciarSocket();

    public void cerrarSocket();

    public void recibirDatos();

    public void enviarDatos();

    public void desempaquetarDatos();
    
    public void iniciarHilo();
}
