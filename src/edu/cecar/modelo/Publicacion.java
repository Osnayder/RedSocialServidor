/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.modelo;

import java.sql.Time;
import java.util.Date;

/** Clase: 
 * 
 * @version: 1.0
 *  
 * @sincelejo: 21/08/2019
 * 
 * Fecha de Modificación: 
 * 
 * @author: Osnayder Conde Rodriguez
 * 
 * Copyrigth: CECAR
 */

public class Publicacion {
    
    private static final long serialVersionUID = 1000L;
    int id;
    byte[] cuerpo;
    int megusta;
    int nomegusta;
    Date fecha;
    Time hora;
    int tipo_privacidad;
    
    public Publicacion(){}
    public Publicacion(int id, byte[] cuerpo, int megusta, int nomegusta, Date fecha, Time hora, int tipo_privacidad) {
        this.id = id;
        this.cuerpo = cuerpo;
        this.megusta = megusta;
        this.nomegusta = nomegusta;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo_privacidad = tipo_privacidad;
    }

    public int getTipo_privacidad() {
        return tipo_privacidad;
    }

    public void setTipo_privacidad(int tipo_privacidad) {
        this.tipo_privacidad = tipo_privacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(byte[] cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getMegusta() {
        return megusta;
    }

    public void setMegusta(int megusta) {
        this.megusta = megusta;
    }

    public int getNomegusta() {
        return nomegusta;
    }

    public void setNomegusta(int nomegusta) {
        this.nomegusta = nomegusta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}
