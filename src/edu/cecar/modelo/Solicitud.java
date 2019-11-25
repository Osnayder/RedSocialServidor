package edu.cecar.modelo;

import java.io.Serializable;
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

public class Solicitud implements Serializable{

    private static final long serialVersionUID = 1000L;
    private int envia;
    private int recibe;
    private int estado;
    private java.sql.Date fecha;

    public Solicitud() {
    }

    public Solicitud(int envia, int recibe, int estado, Date fecha) {
        this.envia = envia;
        this.recibe = recibe;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getEnvia() {
        return envia;
    }

    public void setEnvia(int envia) {
        this.envia = envia;
    }

    public int getRecibe() {
        return recibe;
    }

    public void setRecibe(int recibe) {
        this.recibe = recibe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
