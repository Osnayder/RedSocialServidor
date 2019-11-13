/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.modelo;

import java.io.Serializable;

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

public class Archivo implements Serializable{

    private static final long serialVersionUID = 1000L;
    private Object objecto;
    private String OperacionEnvio;
    private int operacionInterna;

    public Archivo(String OperacionEnvio, int operacionInterna, Object objecto) {
        this.objecto = objecto;
        this.OperacionEnvio = OperacionEnvio;
        this.operacionInterna = operacionInterna;
    }

    public Object getObjecto() {
        return objecto;
    }

    public void setObjecto(Object objecto) {
        this.objecto = objecto;
    }

    public String getOperacionEnvio() {
        return OperacionEnvio;
    }

    public void setOperacionEnvio(String OperacionEnvio) {
        this.OperacionEnvio = OperacionEnvio;
    }

    public int getOperacionInterna() {
        return operacionInterna;
    }

    public void setOperacionInterna(int operacionInterna) {
        this.operacionInterna = operacionInterna;
    }
	
    
    
}
