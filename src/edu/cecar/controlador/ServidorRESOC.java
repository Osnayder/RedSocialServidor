/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.controlador;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.componentes.comunicaciones.ServerSocketObjeto;
import edu.cecar.modelo.Archivo;
import edu.cecar.persistencia.BD;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class ServidorRESOC {

    public ServidorRESOC(int puerto){
        System.out.println("Servidor de Archivos Montado");
				
        ServerSocketObjeto serverSocket = new ServerSocketObjeto(puerto); 
        boolean sw = true;
			
            while (sw) {
                try {
                    Object object = serverSocket.getEntrada().readObject();		
                    Archivo archivo = (Archivo)object;
					
                    if (archivo.getOperacionEnvio().equals("Subida")) { 
                            BD.guardar(archivo);
                    } else {
                            // aqui va el codigo relacionado cuando se valla a descargar algo.	
                    }	
                } catch (IOException e) {		
                    System.out.println("Conexion cerrada de manera inesperada. " + e);
                    sw = false;
                } catch (ClassNotFoundException e) {	
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    
    public static void main(String[] args) {
       new ServidorRESOC(17000);
    }

}
