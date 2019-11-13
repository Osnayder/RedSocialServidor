/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.persistencia;

import com.mysql.cj.MysqlType;
import static com.mysql.cj.MysqlType.MEDIUMBLOB;
import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.controlador.ServidorRESOC;
import edu.cecar.modelo.Archivo;
import edu.cecar.modelo.Sesion;
import edu.cecar.modelo.Usuario;
import java.sql.Blob;
import java.sql.CallableStatement;
import static java.sql.JDBCType.BLOB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class BD {

    public static void agregarUsuario(Usuario archivo) {
        
        int identificacion  = archivo.getIdUsuario();
        String nombres      = archivo.getNombres();
        String apellidos    = archivo.getApellidos();
        java.sql.Date fecha = (java.sql.Date)archivo.getFechanacimiento();
        byte foto[]         = archivo.getFoto();
        String direccion    = archivo.getDireccion();
        java.sql.Date ultimaconexion = (java.sql.Date)archivo.getUlitmaconexion();
        boolean estadoconexion       = false;
        String departamento = archivo.getDepartamento();
        String descripcion  = archivo.getDescripcion();
        String contrasena   = archivo.getContrasena();
        
        
        ConectarMySQL conexion = new ConectarMySQL();
        CallableStatement procedimiento ;
        
        try {
            procedimiento = conexion.getInstance().prepareCall("{call agregarusuario (?,?,?,?,?,?,?,?,?,?,?)}");
            procedimiento.setInt(1,identificacion);
            procedimiento.setString(2,nombres);
            procedimiento.setString(3,apellidos);
            procedimiento.setDate(4, fecha);
            procedimiento.setBytes(5, foto);
            procedimiento.setString(6,direccion);
            procedimiento.setDate(7, ultimaconexion);
            procedimiento.setBoolean(8, estadoconexion);
            procedimiento.setString(9,departamento);
            procedimiento.setString(10,descripcion);
            procedimiento.setString(11,contrasena);
            procedimiento.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void consultaInicioSesion(Sesion sesion){
        
    }
    
}
