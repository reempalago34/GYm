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
import java.util.ArrayList;

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
    
    public Usuario iniciarSesion(String nombre, String contraseña){
        try {
            
            ConexionBD.getInstance();

            String sql = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";

            PreparedStatement ps =
                ConexionBD.conexion.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));

                return usuario;
            }

            

        } catch(SQLException ex){
            System.out.println("Error al iniciar sesión: " + ex.getMessage());
        }
        return null;
    }
    
    public Usuario buscarUsuario(int id) {
        try {
            ConexionBD.getInstance();

            String sql = "SELECT * FROM usuario WHERE idUsuario = ?";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));

                return usuario;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
    
    
    public boolean actualizarUsuario(Usuario usuario) {

        try {

            ConexionBD.getInstance();

            String sql = "UPDATE usuario SET nombre=?, telefono=?, correo=?, contraseña=?, rol=? WHERE idUsuario=?";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getTelefono());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getRol());
            ps.setInt(6, usuario.getIdUsuario());

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

            return false;
        }
    }
        
    public boolean eliminarUsuario(int id) {

        try {

            ConexionBD.getInstance();

            String sql = "DELETE FROM usuario WHERE idUsuario=?";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);

        ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

            return false;
        }
    }
    
    public ArrayList<Usuario> listarUsuarios() {

        ArrayList<Usuario> lista = new ArrayList<>();

        try {

            ConexionBD.getInstance();

            String sql = "SELECT * FROM usuario";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));

                lista.add(usuario);
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return lista;
    }
    public Usuario buscarPorNombre(String nombre) {

    try {

        ConexionBD.getInstance();

        String sql = "SELECT * FROM usuario WHERE nombre=?";

        PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);

        ps.setString(1, nombre);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setContraseña(rs.getString("contraseña"));
            usuario.setRol(rs.getString("rol"));

            return usuario;
        }

    } catch (SQLException ex) {

        System.out.println(ex.getMessage());
    }

        return null;
    }
}
