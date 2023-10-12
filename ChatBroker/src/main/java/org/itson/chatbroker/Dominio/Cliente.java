/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.chatbroker.Dominio;

import java.net.InetAddress;
import static java.net.InetAddress.getLocalHost;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Mancinas
 */
public class Cliente {
    int id;
    String nombre;
    
    public Cliente() {
    }
 
    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
