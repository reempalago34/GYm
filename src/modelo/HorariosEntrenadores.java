/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author erick
 */
public class HorariosEntrenadores {
    public class HorarioEntrenador {

    private int idHorario;
    private int idEntrenador;
    private String dia;
    private String horaInicio;
    private String horaFin;

    public HorarioEntrenador() {
    }

        public int getIdHorario() {
            return idHorario;
        }

        public void setIdHorario(int idHorario) {
            this.idHorario = idHorario;
        }

        public int getIdEntrenador() {
            return idEntrenador;
        }

        public void setIdEntrenador(int idEntrenador) {
            this.idEntrenador = idEntrenador;
        }

        public String getDia() {
            return dia;
        }

        public void setDia(String dia) {
            this.dia = dia;
        }

        public String getHoraInicio() {
            return horaInicio;
        }

        public void setHoraInicio(String horaInicio) {
            this.horaInicio = horaInicio;
        }

        public String getHoraFin() {
            return horaFin;
        }

        public void setHoraFin(String horaFin) {
            this.horaFin = horaFin;
        }

    
}
}
