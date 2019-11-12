/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.cecar.controlador;

import edu.cecar.componentes.ConectarMySQL;
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

    public static void main(String[] args) {
       
      ConectarMySQL conexion = new ConectarMySQL();
       
       PreparedStatement preparedStatement;
        try {
            preparedStatement = conexion.getInstance().prepareStatement("SELECT * FROM departamentos");
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()){
                System.out.println (rs.getInt(1)+ "   "+rs.getString(2)+"  "+rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServidorRESOC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
