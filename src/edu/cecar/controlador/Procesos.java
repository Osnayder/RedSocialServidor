/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.controlador;

import edu.cecar.modelo.Archivo;
import edu.cecar.modelo.Usuario;
import edu.cecar.persistencia.BD;

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

public class Procesos {
    
    // 1. Se va a registra un usuario en la base de datos.
    // 2. Cosulta a BD para iniciar sesion

    public static void iniciar(Archivo archivoRecibido){
     
        switch(archivoRecibido.getOperacionInterna()){
            case 1: 
                BD.agregarUsuario((Usuario)archivoRecibido.getObjecto());
                break;
            case 2:
                
                break;
        }
    }
}
