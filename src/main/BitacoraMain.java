/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imss.sivimss.ods.prefune.on.bitacora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author pnolasco
 */
public class BitacoraMain {

     private static Connection connection;
     private static ResultSet resultSet;
     private static PreparedStatement statement;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
       
    }
    
    public static String consultarInformacion(String usuario, String pasword, String url, String tabla, String condicion) throws SQLException {

        List<String> informacion = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM " + tabla + " WHERE " + condicion;
            connection = DBConexion.getConnection(usuario, pasword, url);
            statement = connection.prepareStatement(consulta);
            resultSet = (ResultSet) statement.executeQuery();
            ResultSetMetaData rsMd = resultSet.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            int colum=1;
            //Establecer como cabezeras el nombre de las colimnas
            HashMap hashMap= new HashMap();
            while (resultSet.next()) {
                Object[] fila = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    hashMap.put(rsMd.getColumnLabel(colum), resultSet.getObject(i + 1));
                    colum++;
                }
                informacion.add(hashMap.toString());
            }
             if (!resultSet.next()) {
                informacion.add("");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                DBConexion.desconection(connection);
            }
            if (resultSet!=null) {
                DBConexion.close_resulset(resultSet);
            }
            if (statement!=null) {
                DBConexion.close_stament(statement);
            }
        }
        return informacion.get(0).toString();
    }
    
    public static void insertarInformacion(String usuario, String pasword, String url, String tabla, Integer idTipoTransaccion, String datoAfectado, String datoActual,Integer idUsuario) throws SQLException {
        try {
            String insert = "INSERT INTO SVH_BITACORA (ID_TIPO_TRANSACCION, "
                    + "DES_TABLA, DES_DATO_AFECTADO, DES_DATO_ACTUAL, ID_USUARIO) "
                    + "VALUES(?, ?, ?, ?,?);";
            connection = DBConexion.getConnection(usuario, pasword, url);
           
            statement = connection.prepareStatement(insert);
            statement.setInt(1, idTipoTransaccion);
            statement.setString(2, tabla);
            statement.setString(3, datoAfectado);
            statement.setString(4, datoActual);
            statement.setInt(5, idUsuario);
            statement.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                DBConexion.desconection(connection);
            }
            if (resultSet!=null) {
                DBConexion.close_resulset(resultSet);
            }
            if (statement!=null) {
                DBConexion.close_stament(statement);
            }
        }
      
    }
    
    
    
}
