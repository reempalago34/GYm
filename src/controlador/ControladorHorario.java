/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ConexionBD;
import modelo.HorariosEntrenadores;
import java.util.ArrayList;
import modelo.Entrenador;

/**
 *
 * @author erick
 */
public class ControladorHorario {
  public ArrayList<HorariosEntrenadores> listarHorarios() {

        ArrayList<HorariosEntrenadores> lista = new ArrayList<>();

        try {

            ConexionBD.getInstance();

            String sql =
                    "SELECT h.idHorario, h.idEntrenador, e.nombre, h.dia, h.horaInicio, h.horaFin " +
                    "FROM horario_entrenador h " +
                    "INNER JOIN entrenador e " +
                    "ON h.idEntrenador = e.idEntrenador";

            PreparedStatement ps =
                    ConexionBD.conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                HorariosEntrenadores horario = new HorariosEntrenadores();

                horario.setIdHorario(rs.getInt("idHorario"));
                horario.setIdEntrenador(rs.getInt("idEntrenador"));
                horario.setNombreEntrenador(rs.getString("nombre"));
                horario.setDia(rs.getString("dia"));
                horario.setHoraInicio(rs.getString("horaInicio"));
                horario.setHoraFin(rs.getString("horaFin"));

                lista.add(horario);
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return lista;
    }
    public boolean registrarHorario(HorariosEntrenadores horario) {

        try {

            ConexionBD.getInstance();

            String sql = "INSERT INTO horario_entrenador "
                    + "(idEntrenador, dia, horaInicio, horaFin) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);

            ps.setInt(1, horario.getIdEntrenador());
            ps.setString(2, horario.getDia());
            ps.setString(3, horario.getHoraInicio());
            ps.setString(4, horario.getHoraFin());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }
    public HorariosEntrenadores buscarHorario(int idHorario) {

        try {

            ConexionBD.getInstance();

            String sql = "SELECT * FROM horario_entrenador WHERE idHorario=?";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);
            ps.setInt(1, idHorario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                HorariosEntrenadores horario = new HorariosEntrenadores();

                horario.setIdHorario(rs.getInt("idHorario"));
                horario.setIdEntrenador(rs.getInt("idEntrenador"));
                horario.setDia(rs.getString("dia"));
                horario.setHoraInicio(rs.getString("horaInicio"));
                horario.setHoraFin(rs.getString("horaFin"));

                return horario;
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

        return null;
    }
    
    public Entrenador buscarPorNombre(String nombre){

        try{

            ConexionBD.getInstance();

            String sql = "SELECT * FROM entrenador WHERE nombre=?";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                Entrenador e = new Entrenador();

                e.setIdEntrenador(rs.getInt("idEntrenador"));
                e.setNombre(rs.getString("nombre"));

                return e;
            }

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }
    
    public boolean eliminarHorario(int idHorario) {

        try {

            ConexionBD.getInstance();

            String sql = "DELETE FROM horario_entrenador WHERE idHorario=?";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);

            ps.setInt(1, idHorario);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean actualizarHorario(HorariosEntrenadores horario) {

        try {

            ConexionBD.getInstance();

            String sql = "UPDATE horario_entrenador "
                    + "SET idEntrenador=?, dia=?, horaInicio=?, horaFin=? "
                    + "WHERE idHorario=?";

            PreparedStatement ps = ConexionBD.conexion.prepareStatement(sql);

            ps.setInt(1, horario.getIdEntrenador());
            ps.setString(2, horario.getDia());
            ps.setString(3, horario.getHoraInicio());
            ps.setString(4, horario.getHoraFin());
            ps.setInt(5, horario.getIdHorario());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
            return false;

        }
    }
}
