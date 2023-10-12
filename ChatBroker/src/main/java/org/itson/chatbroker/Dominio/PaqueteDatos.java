/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.chatbroker.Dominio;

import java.io.Serializable;

/**
 *
 * @author Gabriel Mancinas
 */
/*Se implementa la interfaz Serializable para que los datos incluidos dentro de esta clase tengan la capacidad
de convertirse en bits para poder ser enviadas a través de la RED.
*/
public class PaqueteDatos implements Serializable{
    private String nombre;
    private String ip;
    private String mensaje;
    private Enum para;
    
    public PaqueteDatos(String nombre, String mensaje,String ip) {
        this.nombre = nombre;
        this.ip = ip;
        this.mensaje = mensaje;
    }

    public PaqueteDatos(String nombre, String mensaje, String ip, Enum para) {
        this.nombre = nombre;
        this.ip = ip;
        this.mensaje = mensaje;
        this.para = para;
    }

    public PaqueteDatos(Enum para) {
        this.para = para;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Enum getPara() {
        return para;
    }

    public void setPara(Enum para) {
        this.para = para;
    }
    
    
}
