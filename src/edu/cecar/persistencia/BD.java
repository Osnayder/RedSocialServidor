package edu.cecar.persistencia;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelo.Sesion;
import edu.cecar.modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.SQLException;
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

    public static void agregarUsuario(Usuario usuarioNuevo) {
        
        ConectarMySQL conexion = new ConectarMySQL();
        CallableStatement procedimiento_1, procedimiento_2,procedimiento_3;
        
        int identificacion  = usuarioNuevo.getIdUsuario();
        String nombres      = usuarioNuevo.getNombres();
        String apellidos    = usuarioNuevo.getApellidos();
        java.sql.Date fecha = (java.sql.Date)usuarioNuevo.getFechanacimiento();
        byte foto[]         = usuarioNuevo.getFoto();
        String direccion    = usuarioNuevo.getDireccion();
        java.sql.Date ultimaconexion = (java.sql.Date)usuarioNuevo.getUlitmaconexion();
        boolean estadoconexion       = false;
        String departamento = usuarioNuevo.getDepartamento();
        String descripcion  = usuarioNuevo.getDescripcion();
        String contrasena   = usuarioNuevo.getContrasena();
        
        try {
            procedimiento_1 = conexion.getInstance().prepareCall("{call agregarusuario (?,?,?,?,?,?,?,?,?,?,?)}");
            procedimiento_1.setInt(1,identificacion);
            procedimiento_1.setString(2,nombres);
            procedimiento_1.setString(3,apellidos);
            procedimiento_1.setDate(4, fecha);
            procedimiento_1.setBytes(5, foto);
            procedimiento_1.setString(6,direccion);
            procedimiento_1.setDate(7, ultimaconexion);
            procedimiento_1.setBoolean(8, estadoconexion);
            procedimiento_1.setString(9,departamento);
            procedimiento_1.setString(10,descripcion);
            procedimiento_1.setString(11,contrasena);
            procedimiento_1.execute();
            System.out.println("Se registro el usuario en la BD");
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            procedimiento_2 = conexion.getInstance().prepareCall("{call agregartelefono(?,?)}");
            for(int i=0; i<usuarioNuevo.getTelefonos().size(); i++){
                procedimiento_2.setLong(1,usuarioNuevo.getTelefonos().get(i));
                procedimiento_2.setInt(2, identificacion);
                procedimiento_2.execute();
            }
            System.out.println("Se registro el telefono");
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            procedimiento_3 = conexion.getInstance().prepareCall("{call agregarcelular(?,?)}");
            for(int i=0; i<usuarioNuevo.getCelular().size(); i++){
                procedimiento_3.setLong(1,usuarioNuevo.getCelular().get(i));
                procedimiento_3.setInt(2, identificacion);
                procedimiento_3.execute();
            }
            System.out.println("Se registro el celular");
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void consultaInicioSesion(Sesion sesion){
        
    }
    
}
