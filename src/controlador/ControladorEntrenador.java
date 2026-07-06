/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Entrenador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ConexionBD;

/**
 *
 * @author erick
 */
public class ControladorEntrenador {
    public boolean guardarEntrenador(Entrenador entrenador) {
        try {

            ConexionBD.getInstance();

            String sql = "INSERT INTO entrenador(nombre, telefono, correo, especialidad) VALUES(?,?,?,?)";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getTelefono());
            ps.setString(3, entrenador.getCorreo());
            ps.setString(4, entrenador.getEspecialidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        }
    }

    public ArrayList<Entrenador> listarEntrenadores() {
        ArrayList<Entrenador> lista = new ArrayList<>();

        try {

            ConexionBD.getInstance();

            String sql = "SELECT * FROM entrenador";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Entrenador e = new Entrenador();

                e.setIdEntrenador(rs.getInt("idEntrenador"));
                e.setNombre(rs.getString("nombre"));
                e.setTelefono(rs.getString("telefono"));
                e.setCorreo(rs.getString("correo"));
                e.setEspecialidad(rs.getString("especialidad"));

                lista.add(e);
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return lista;
    }

    public Entrenador buscarEntrenador(int id) {
        try {

            ConexionBD.getInstance();

            String sql = "SELECT * FROM entrenador WHERE idEntrenador=?";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Entrenador e = new Entrenador();

                e.setIdEntrenador(rs.getInt("idEntrenador"));
                e.setNombre(rs.getString("nombre"));
                e.setTelefono(rs.getString("telefono"));
                e.setCorreo(rs.getString("correo"));
                e.setEspecialidad(rs.getString("especialidad"));

                return e;

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

        return null;
    }

    public boolean actualizarEntrenador(Entrenador entrenador) {
        try {

            ConexionBD.getInstance();

            String sql = "UPDATE entrenador SET nombre=?, telefono=?, correo=?, especialidad=? WHERE idEntrenador=?";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getTelefono());
            ps.setString(3, entrenador.getCorreo());
            ps.setString(4, entrenador.getEspecialidad());
            ps.setInt(5, entrenador.getIdEntrenador());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;

        }

    }

    public boolean eliminarEntrenador(int id) {
        try {

            ConexionBD.getInstance();

            String sql = "DELETE FROM entrenador WHERE idEntrenador=?";

            PreparedStatement ps = ConexionBD.getConexion().prepareStatement(sql);

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;

        }

    }
}
