package edu.cecar.persistencia;

import edu.cecar.componentes.ConectarMySQL;
import edu.cecar.modelo.Publicacion;
import edu.cecar.modelo.Red;
import edu.cecar.modelo.Sesion;
import edu.cecar.modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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

public class BD {

    public static void agregarUsuario(Usuario usuarioNuevo) {
        
        ConectarMySQL conexion = new ConectarMySQL();
        CallableStatement procedimiento_1, procedimiento_2,procedimiento_3,procedimiento_4;
        
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
        
        try {
            procedimiento_4 = conexion.getInstance().prepareCall("{call agregarredsocial(?,?,?)}");
            for(int i=0; i<usuarioNuevo.getOtrasredes().size(); i++){
                procedimiento_4.setString(1,usuarioNuevo.getOtrasredes().get(i).getNombre());
                procedimiento_4.setString(2,usuarioNuevo.getOtrasredes().get(i).getCuenta());
                procedimiento_4.setInt(3, identificacion);
                procedimiento_4.execute();
            }
            System.out.println("Se registro la red social");
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.cerrarConexion();
    }
    
    
    
    public static  Date consultaInicioSesion(Sesion sesion){
        ConectarMySQL conexion = new ConectarMySQL();
        CallableStatement procedimiento_1;
        Date date = null;
        
        try {
            procedimiento_1 = conexion.getInstance().prepareCall("{call consultarsesion(?,?,?)}");
            procedimiento_1.setInt(1,sesion.getIdUsuario());
            procedimiento_1.setString(2,sesion.getContrasena());
            procedimiento_1.registerOutParameter(3,Types.DATE);
            procedimiento_1.execute();
            date = procedimiento_1.getDate(3);
            System.out.println("Se cosulto el usuario en BD");
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.cerrarConexion();
        return date;
    }
    
    public static Usuario consultarUsuario(int codigo){
        Usuario usuario           = null;
        ArrayList<Long> celulares = null;
        ArrayList<Long> telefonos = null;
        ArrayList<Red> otrasredes = null;
        PreparedStatement procedimientoN1,procedimientoN2,procedimientoN3,procedimientoN4;
        ConectarMySQL conexion    = new ConectarMySQL();
        
        try {
            telefonos = new ArrayList();
            procedimientoN2 = conexion.getInstance().prepareStatement("SELECT * FROM telefonos WHERE idusuario_fk4 = ?");
            procedimientoN2.setInt(1, codigo);
            procedimientoN2.execute();
            ResultSet resultSet2 = procedimientoN2.getResultSet();
            while(resultSet2.next()){
                telefonos.add(resultSet2.getLong(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            celulares = new ArrayList();
            procedimientoN3 = conexion.getInstance().prepareStatement("SELECT * FROM celulares WHERE fk_idusuario6 = ?");
            procedimientoN3.setInt(1, codigo);
            procedimientoN3.execute();
            ResultSet resultSet3 = procedimientoN3.getResultSet();
            while(resultSet3.next()){
                celulares.add(resultSet3.getLong(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            otrasredes = new ArrayList();
            Red red = null;
            procedimientoN4 = conexion.getInstance().prepareStatement("SELECT * FROM redessociales WHERE fk_usuario5 = ?");
            procedimientoN4.setInt(1, codigo);
            procedimientoN4.execute();
            ResultSet resultSet4 = procedimientoN4.getResultSet();
            while(resultSet4.next()){
                red = new Red(resultSet4.getString(2),resultSet4.getString(3));
                otrasredes.add(red);
                red = null;
            }
            System.out.println("====**===");
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            procedimientoN1 = conexion.getInstance().prepareStatement("SELECT * FROM usuarios WHERE idusuario = ?");
            procedimientoN1.setInt(1,codigo);
            procedimientoN1.execute();
            ResultSet resultSet = procedimientoN1.getResultSet();
            
            if(resultSet.next()){
                int identificacion  = resultSet.getInt(1);
                String nombres      = resultSet.getString(2);
                String apellidos    = resultSet.getString(3);
                java.sql.Date fecha = resultSet.getDate(4);
                byte foto[]         = resultSet.getBytes(5);
                String direccion    = resultSet.getString(6);
                java.sql.Date ultimaconexion = resultSet.getDate(7);
                boolean estadoconexion       = resultSet.getBoolean(8);
                String departamento = resultSet.getString(9);
                String descripcion  = resultSet.getString(10);
                String contrasena   = resultSet.getString(11);
                usuario = new Usuario(identificacion, nombres, apellidos, fecha, foto, direccion, celulares, telefonos, 
                                      otrasredes, ultimaconexion, estadoconexion, departamento, descripcion, contrasena);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.cerrarConexion();
        return usuario;
    }
    
    public static void agregarPublicacion(Publicacion publicacion){
        ConectarMySQL conexion = new ConectarMySQL();
        PreparedStatement procedimiento = null;
        
        try {
            procedimiento = conexion.getInstance().prepareStatement("INSERT INTO publicaciones VALUES(null,?,?,?,?,?,?,?,?)");
            procedimiento.setInt(1,publicacion.getMegusta());
            procedimiento.setInt(2,publicacion.getNomegusta());
            procedimiento.setBytes(3, publicacion.getCuerpo());
            procedimiento.setString(4, publicacion.getTetxo());
            procedimiento.setDate(5, publicacion.getFecha());
            procedimiento.setTime(6, publicacion.getHora());
            procedimiento.setInt(7, publicacion.getTipo_privacidad());
            procedimiento.setInt(8, publicacion.getIdUsuario());
            procedimiento.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        conexion.cerrarConexion();
    }
    
    public static ArrayList<Publicacion> consultarPublicaciones(Sesion sesion){
        ConectarMySQL conexion          = new ConectarMySQL();
        PreparedStatement procedimiento = null;
        ArrayList<Publicacion> listaPublicaciones  = null;
       
        
        try {
            procedimiento = conexion.getInstance().prepareStatement("SELECT * FROM publicaciones WHERE idusuario_fk1 = ?");
            procedimiento.setInt(1,sesion.getIdUsuario());
            procedimiento.execute();
            
            listaPublicaciones    = new ArrayList();
            ResultSet resultSet = procedimiento.getResultSet();
            while(resultSet.next()){ 
               listaPublicaciones.add(new Publicacion(0,resultSet.getBytes("cuerpo"),resultSet.getString(5),resultSet.getInt(2),
                                         resultSet.getInt(3),resultSet.getDate(6),resultSet.getTime(7),resultSet.getInt(8)));
            }
            System.out.println("Se consulto de manera exitosa las publicaciones");
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i=0; i<2; i++){
            System.out.println("Me gustas: "+listaPublicaciones.get(i).getMegusta());
            System.out.println("Me no gustas: "+listaPublicaciones.get(i).getNomegusta());
            System.out.println("Me texto: "+listaPublicaciones.get(i).getTetxo());
            System.out.println("Me fecha: "+listaPublicaciones.get(i).getFecha());
            System.out.println("Me hora: "+listaPublicaciones.get(i).getHora());
            if(listaPublicaciones.get(i).getCuerpo()!=null){
                System.out.println("La foto existe");
            }
        }
        
        return listaPublicaciones;
    }  
}
