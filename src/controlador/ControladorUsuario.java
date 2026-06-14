/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.ConexionBD;
import modelo.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author erick
 */
public class ControladorUsuario {
    public boolean guardarUsuario(Usuario usuario){
        try {
            ConexionBD.getInstance();
            
            String sql = "INSERT INTO usuario(nombre, telefono, correo, contraseña) VALUES(?,?,?,?)";
            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getTelefono());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContraseña());
            
            ps.executeUpdate();
            
            return true;
        } catch(SQLException ex){
            System.out.println("Error al guardar: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean iniciarSesion(String nombre, String contraseña){
        try {
            
            ConexionBD.getInstance();

            String sql = "SELECT contraseña FROM usuario WHERE nombre = ?";

            PreparedStatement ps =
                ConexionBD.conexion.prepareStatement(sql);

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                String contraseñaBD = rs.getString("contraseña");

                return contraseñaBD.equals(contraseña);
            }

            return false;

        } catch(SQLException ex){
            System.out.println("Error al iniciar sesión: "
                + ex.getMessage());
            return false;
        }
    }
}
