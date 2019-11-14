/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.modelo;

import java.io.Serializable;
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

public class Sesion implements Serializable {
    
    private static final long serialVersionUID = 1000L;
    private int idUsuario;
    private String contrasena;
    private Date ultimaConexion;
    private boolean estado;

    public Sesion(int idUsuario, String contrasena, Date ultimaConexion, boolean estado) {
        this.idUsuario = idUsuario;
        this.contrasena = contrasena;
        this.ultimaConexion = ultimaConexion;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
