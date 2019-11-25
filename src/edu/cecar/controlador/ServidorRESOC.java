package edu.cecar.controlador;

import edu.cecar.componentes.comunicaciones.ServerSocketObjeto;
import edu.cecar.modelo.Archivo;
import edu.cecar.modelo.Publicacion;
import edu.cecar.modelo.Sesion;
import edu.cecar.modelo.Solicitud;
import edu.cecar.modelo.Usuario;
import edu.cecar.modelo.UsuarioConsulta;
import edu.cecar.persistencia.BD;
import java.io.IOException;
import java.util.ArrayList;
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
                break;
            case 2:
                 Sesion archivoSesion = new Sesion(((Sesion)archivoRecibido.getObjecto()).getIdUsuario(), 
                                        ((Sesion)archivoRecibido.getObjecto()).getContrasena(),null, false);
                 
                 Date date = BD.consultaInicioSesion(archivoSesion);
                 archivoSesion.setUltimaConexion(date);
                            
                    try {
                        serverSocket.getSalida().writeObject(archivoSesion);
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                    }                
                break;
                
            case 3:
                Integer intero = (Integer)archivoRecibido.getObjecto();
                Usuario usuario = BD.consultarUsuario(intero.intValue());
                
                    try {
                        serverSocket.getSalida().writeObject(usuario);
                    } catch (IOException ex) {
                        Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                
                break;
            case 4: 
                BD.agregarPublicacion((Publicacion)archivoRecibido.getObjecto());
                break;
            case 5:
                ArrayList<Publicacion> publicacioEnviar = BD.consultarPublicaciones((Sesion)archivoRecibido.getObjecto());
                        try {
                            serverSocket.getSalida().writeObject(publicacioEnviar);
                        } catch (IOException ex) {
                            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                break;
            case 6:
                String valorBusqueda = (String)archivoRecibido.getObjecto();
                ArrayList<UsuarioConsulta> listaUsuaario12 =  BD.consultarUsuarioB1(valorBusqueda);
                        try {
                            serverSocket.getSalida().writeObject(listaUsuaario12);
                        } catch (IOException ex) {
                            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                break;
            case 7:
                Solicitud valorBusquedaString = (Solicitud)archivoRecibido.getObjecto();
                BD.agregarSolicitud(valorBusquedaString);
                
                break;
            case 8:
                Integer identifiacion = (Integer)archivoRecibido.getObjecto();
                ArrayList<UsuarioConsulta> listaUsuario13 =  BD.consultarAmigos(identifiacion);
                        try {
                            serverSocket.getSalida().writeObject(listaUsuario13);
                        } catch (IOException ex) {
                            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                break;
            case 9:
                Integer identificacion = (Integer)archivoRecibido.getObjecto();
                ArrayList<UsuarioConsulta> listaUsuario = BD.consultarSolicitudes(identificacion);
                        try {
                            serverSocket.getSalida().writeObject(listaUsuario);
                        } catch (IOException ex) {
                            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
                        }   
                break;
            case 10:
                Solicitud valorSolicitud = (Solicitud) archivoRecibido.getObjecto();
                BD.aceptarSolicitud(valorSolicitud);
                break;
            case 11:
                Sesion sesionA = (Sesion) archivoRecibido.getObjecto();
                BD.eliminarUsuario(sesionA);
                break;
        }
    }
    
     public static void main(String[] args){
        new ServidorRESOC(17000);
    }
}
