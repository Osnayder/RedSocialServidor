package edu.cecar.modelo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

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

public class Publicacion implements Serializable{
    
    private static final long serialVersionUID = 1000L;
    private int idUsuario;
    private byte[] cuerpo;
    private String tetxo;
    private int megusta;
    private int nomegusta;
    private java.sql.Date fecha;
    private java.sql.Time hora;
    private int tipo_privacidad;
    //private int tipo_multimedia;
    
    public Publicacion(){}

    public Publicacion(int idUsuario, byte[] cuerpo, String tetxo, int megusta, int nomegusta, Date fecha, Time hora, int tipo_privacidad) {
        this.idUsuario = idUsuario;
        this.cuerpo = cuerpo;
        this.tetxo = tetxo;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public byte[] getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(byte[] cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getTetxo() {
        return tetxo;
    }

    public void setTetxo(String tetxo) {
        this.tetxo = tetxo;
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
