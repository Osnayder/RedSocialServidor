package edu.cecar.controlador;

import java.io.IOException;

import edu.cecar.componentes.Utilidades;
import edu.cecar.componentes.comunicaciones.ServerSocketObjeto;
import edu.cecar.modelo.Archivo;


public class ServidorArchivo {
	
	private String ruta = "ArchivosCompartidos/";
	
	public ServidorArchivo(int puerto) {
				
            System.out.println("Servidor de Archivos Montado");
				
            ServerSocketObjeto serverSocket = new ServerSocketObjeto(puerto); 
			
            boolean sw = true;
			
		while (sw) {

                    try {
			
			Object object = serverSocket.getEntrada().readObject();
					
			Archivo archivo = (Archivo)object;
					
			if (archivo.getOperacion().equals("Subida")) { 
				Utilidades.escribirAchivo(ruta + archivo.getNombre(), archivo.getBytes());
			} else {
				byte[] bytes = Utilidades.getBytes(ruta + archivo.getNombre());
				archivo.setBytes(bytes);
				serverSocket.getSalida().writeObject(archivo);	
			}
				
                    } catch (IOException e) {
					
			System.out.println("Conexi�n cerrada de manera inesperada. " + e);
			sw = false;
					
                    } catch (ClassNotFoundException e) {
					
			e.printStackTrace();
					
                    } catch (Exception e) {
					
			e.printStackTrace();
                    }
			
                }
			
        }

}
