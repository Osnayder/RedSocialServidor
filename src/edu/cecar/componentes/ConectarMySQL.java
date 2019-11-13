package edu.cecar.componentes;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

public class ConectarMySQL {
        private Connection connection;

	public ConectarMySQL() {
            
            if(connection==null){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                        
                        Properties properties = new Properties();
                        properties.load(new FileInputStream("Conexion.properties"));
			
			String url= "jdbc:mysql://"+properties.getProperty("host")+ ":"+properties.getProperty("port")+"/"+properties.getProperty("baseDatos")+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
			connection=DriverManager.getConnection(url,properties.getProperty("usuario"),properties.getProperty("password"));
                        System.out.println("Conexion a BD exitosa!");
		}catch  (Exception e) {
			System.out.println("Conexion a BD, Error"+e);
		}
            }
	}


	public Connection getInstance(){
		return connection;
	}

	public void cerrarConexion()  {
		try {			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
}
