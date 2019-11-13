/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.controlador;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.componentes.Utilidades;
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
                
                if(archivo.getOperacionEnvio().equals("Subida")){
                    System.out.println("!Se Recibio Petecion de Cliente!");
                    System.out.println("contenido: "+((Usuario)archivo.getObjecto()).getIdUsuario() + " "+((Usuario)archivo.getObjecto()).getNombres()+" "+((Usuario)archivo.getObjecto()).getCelular().get(0)+" "+((Usuario)archivo.getObjecto()).getTelefonos().get(0));
                    procesarSolicitud(archivo);
                }else{
                    
                }
                
                
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
    
   
    private void procesarSolicitud(Archivo archivoRecibido){
        switch(archivoRecibido.getOperacionInterna()){
            case 1: 
                BD.agregarUsuario((Usuario)archivoRecibido.getObjecto());
                System.out.println("Se Agrego Un Nuevo Usuario a RESOC!");
                break;
            case 2:
                
                break;
        }
    }
    
     public static void main(String[] args){
        new ServidorRESOC(17000);
    }
}
