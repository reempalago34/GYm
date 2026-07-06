/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Membresia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ConexionBD;

/**
 *
 * @author erick
 */
public class ControladorMembresia {
    public boolean guardarMembresia(Membresia membresia) {
        try {

            ConexionBD.getInstance();

            String sql = "INSERT INTO membresia(nombre, precio, duracion, descripcion) VALUES(?,?,?,?)";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setString(1, membresia.getNombre());
            ps.setDouble(2, membresia.getPrecio());
            ps.setInt(3, membresia.getDuracion());
            ps.setString(4, membresia.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    
    
    

    public ArrayList<Membresia> listarMembresias() {

        ArrayList<Membresia> lista = new ArrayList<>();

        try {

            ConexionBD.getInstance();

            String sql = "SELECT * FROM membresia";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Membresia m = new Membresia();

                m.setIdMembresia(rs.getInt("idMembresia"));
                m.setNombre(rs.getString("nombre"));
                m.setPrecio(rs.getDouble("precio"));
                m.setDuracion(rs.getInt("duracion"));
                m.setDescripcion(rs.getString("descripcion"));

                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }
    
    public Membresia buscarMembresia(int id) {

        try {

            ConexionBD.getInstance();

            String sql = "SELECT * FROM membresia WHERE idMembresia=?";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Membresia m = new Membresia();

                m.setIdMembresia(rs.getInt("idMembresia"));
                m.setNombre(rs.getString("nombre"));
                m.setPrecio(rs.getDouble("precio"));
                m.setDuracion(rs.getInt("duracion"));
                m.setDescripcion(rs.getString("descripcion"));

                return m;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
    public boolean actualizarMembresia(Membresia membresia) {

        try {

            ConexionBD.getInstance();

            String sql = "UPDATE membresia SET nombre=?, precio=?, duracion=?, descripcion=? WHERE idMembresia=?";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setString(1, membresia.getNombre());
            ps.setDouble(2, membresia.getPrecio());
            ps.setInt(3, membresia.getDuracion());
            ps.setString(4, membresia.getDescripcion());
            ps.setInt(5, membresia.getIdMembresia());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarMembresia(int id) {

        try {

            ConexionBD.getInstance();

            String sql = "DELETE FROM membresia WHERE idMembresia=?";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
