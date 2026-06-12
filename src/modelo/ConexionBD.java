/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author erick
 */
public class ConexionBD {
    public static Connection conexion;
    
    private ConexionBD() {
        try {
            String driverBD = "com.mysql.cj.jdbc.Driver";
            String urlBD = "jdbc:mysql://localhost:3306/Gimnasio";
            String usuarioBD = "root";
            String claveBD ="";
            Class.forName(driverBD);
            conexion = DriverManager.getConnection(urlBD, usuarioBD, claveBD);
            System.out.println("Conexión Exitosa");
        } catch (ClassNotFoundException ex) {
            System.err.println("No encuentro el Dirver:"+ex.getMessage());
        } catch (SQLException ex){
            System.err.println("Error al conectarme:"+ex.getMessage());
        }
    }
    
    public static void desconectar(){
        try {
            conexion.close();
            
        } catch (SQLException ex) {
            System.err.println("Error al desconectarme:" + ex.getMessage());
        }
    }
    
    public static ConexionBD getInstance() {
        return ConexionBDHolder.INSTANCE;
    }
    
    private static class ConexionBDHolder {

        private static final ConexionBD INSTANCE = new ConexionBD();
    }
}
