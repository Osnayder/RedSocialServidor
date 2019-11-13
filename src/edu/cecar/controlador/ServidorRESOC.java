/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.controlador;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.componentes.comunicaciones.ServerSocketObjeto;
import edu.cecar.modelo.Archivo;
import edu.cecar.modelo.Usuario;
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
                
        System.out.println("Servidor montado");
        ServerSocketObjeto serverSocket = new ServerSocketObjeto(puerto);   
        boolean sw = true;
	
        while (sw) {
			
            try {

                Object object = serverSocket.getEntrada().readObject();		
                Archivo archivo = (Archivo)object;
                System.out.println("Se Descargo Exitosamente el Archivo");                            
                Usuario u = (Usuario) archivo.getObjecto();
		              System.out.println("contenido "+u.getNombres());			
            } catch (IOException e) {		
                System.out.println("Conexión Cerrada de Manera Inesperada. " + e); 
                sw = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();		
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    
    public static void main(String[] args){
        new ServidorRESOC(17000);
    }
}
