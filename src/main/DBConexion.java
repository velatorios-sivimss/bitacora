package com.imss.sivimss.ods.prefune.on.bitacora;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author pablonolasco
 */
public class DBConexion {

    private static Driver driver;
    private static String DB_URL_BD;
    private static String DB_USER_BD;
    private static String DB_PASS_BD;

    /**
     * TODO Constructor que inicializa la conexion a la Base de datos
     *
     * @return conn cadena de conexion
     */
    /**
     * TODO Metodo que retorna la conexion a la base de datos
     */
    
    public static synchronized Connection getConnection(String usuario, String pasword, String database) throws SQLException {
        if (driver == null) {
            try {
              
                Class jdbClass = Class.forName("com.mysql.jdbc.Driver");
                driver = (Driver) jdbClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (SQLException e) {
                System.err.println("message conexion:" + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.err.println("message driver:" + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error Conexion");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(database, usuario, pasword);
    }

    /**
     * TODO metodo que cierra el resulset
     *
     * @param rs
     */
    public static void close_resulset(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println("message:" + e.getMessage());

        } catch (Exception e) {
            System.err.println("message:" + e.getMessage());

        }

    }

    /**
     * TODO Metodo que cierra PreparedStatement
     *
     * @param ps
     */
    public static void close_stament(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }

        } catch (SQLException e) {
            System.err.println("message:" + e.getMessage());
        } catch (Exception e) {
            System.err.println("message:" + e.getMessage());
        }
    }

    /**
     * TODO Metodo que desconecta a la base de datos
     */
    public static void desconection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            System.err.println("message error conection" + e.getMessage());
        }
    }

}