package edu.cecar.controlador;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.componentes.comunicaciones.ServerSocketObjeto;
import edu.cecar.modelo.Archivo;
import edu.cecar.modelo.Sesion;
import edu.cecar.modelo.Usuario;
import edu.cecar.persistencia.BD;
import java.io.IOException;
import java.util.Date;
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
    
    ServerSocketObjeto serverSocket;
    
    public ServidorRESOC(int puerto){
                
        System.out.println("Servidor montado");
        
        serverSocket = new ServerSocketObjeto(puerto);   
        boolean sw = true;
	
        while (sw) {
            try {
                System.out.println("entro en ciclo");
                Object object = serverSocket.getEntrada().readObject();
                
                Archivo archivo = (Archivo)object;
                
                if(archivo.getOperacionEnvio().equals("Subida")){
                    System.out.println("!Se Recibio Petecion de Cliente!");
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
                 Sesion archivoSesion = new Sesion(((Sesion)archivoRecibido.getObjecto()).getIdUsuario(), 
                                        ((Sesion)archivoRecibido.getObjecto()).getContrasena(),null, false);
                 
                 Date date = BD.consultaInicioSesion(archivoSesion);
                 archivoSesion.setUltimaConexion(date);
                            
                        try {
                            serverSocket.getSalida().writeObject(archivoSesion);
                            System.out.println(": "+archivoSesion.getIdUsuario()+" "+archivoSesion.getContrasena());
                            System.out.println("Se envio los datos de sesion a usuario");
                        } catch (IOException ex) {
                            System.out.println("Error envinado datos de sesion a usuario");
                            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                        }                
                break;
                
            case 3:
                Integer intero = (Integer)archivoRecibido.getObjecto();
                Usuario usuario = BD.consultarUsuario(intero.intValue());
                
                        try {
                            serverSocket.getSalida().writeObject(usuario);
                            System.out.println("Se envio extraidos de BD usuario");
                        } catch (IOException ex) {
                            System.out.println("Error ==**");
                            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                
                break;
        }
    }
    
     public static void main(String[] args){
        new ServidorRESOC(17000);
    }
}
