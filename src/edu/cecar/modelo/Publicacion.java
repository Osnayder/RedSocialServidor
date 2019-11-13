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
    
    
}
