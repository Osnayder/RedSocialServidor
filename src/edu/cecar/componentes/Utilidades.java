package edu.cecar.componentes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utilidades {

	public static byte[] getBytes(String rutaNombre) throws Exception {
		
		byte[] resultado = new byte[1024];

		File archivo = new File(rutaNombre);
		FileInputStream fileInputStream = new FileInputStream(archivo);
		fileInputStream.read(resultado);
		fileInputStream.close();
		
		return resultado;
	}
	
	public static void escribirAchivo(String rutaNombre, byte[] bytes) throws IOException {
		
		File archivo = new File(rutaNombre);
		FileOutputStream fileOutputStream = new FileOutputStream(archivo);
		fileOutputStream.write(bytes);
		fileOutputStream.close();
		
	}
}
