/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ControladorHorario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import vista.FRMEditarHorario;
import vista.FRMRegistrarHorario;
/**
 *
 * @author erick
 */
public class PanelHorarios extends JPanel {
    private JTable tablaHorarioEntrenadores;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    private JDesktopPane escritorio;
    public PanelHorarios(JDesktopPane escritorio) {

        this.escritorio = escritorio;

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Administración de Horarios de Entrenadores");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        tablaHorarioEntrenadores = new JTable();

        tablaHorarioEntrenadores.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID",
                    "Entrenador",
                    "Dia",
                    "Hora Inicio",
                    "Hora Fin"
                }
        ));
        JScrollPane scroll = new JScrollPane(tablaHorarioEntrenadores);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarHorarios();
        
        btnNuevo.addActionListener(e -> {

            FRMRegistrarHorario frm = new FRMRegistrarHorario();

            escritorio.add(frm);

            frm.setVisible(true);
        });
        
        btnEditar.addActionListener(e -> {

            int fila = tablaHorarioEntrenadores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this,
                        "Seleccione un entrenador.");

                return;
            }

            int id = (int) tablaHorarioEntrenadores.getValueAt(fila, 0);

            ControladorHorario controlador = new ControladorHorario();

            HorariosEntrenadores horario = controlador.buscarHorario(id);

            FRMEditarHorario frm = new FRMEditarHorario(horario);

            escritorio.add(frm);

            frm.setVisible(true);

        });
        btnEliminar.addActionListener(e -> {

            int fila = tablaHorarioEntrenadores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this,
                        "Seleccione un horario.");

                return;
            }

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar este horario?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                int id = (int) tablaHorarioEntrenadores.getValueAt(fila, 0);

                ControladorHorario controlador = new ControladorHorario();

                if (controlador.eliminarHorario(id)) {

                    JOptionPane.showMessageDialog(this,
                            "Horario eliminado correctamente.");

                    cargarHorarios();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No se pudo eliminar el Horario.");
                }
            }

        });
        
        btnActualizar.addActionListener(e -> {
            cargarHorarios();
        });
    }
    public void cargarHorarios() {

    ControladorHorario controlador = new ControladorHorario();

    DefaultTableModel modelo =
            (DefaultTableModel) tablaHorarioEntrenadores.getModel();

    modelo.setRowCount(0);

    for (HorariosEntrenadores h : controlador.listarHorarios()) {

        modelo.addRow(new Object[]{
            h.getIdHorario(),
            h.getNombreEntrenador(),
            h.getDia(),
            h.getHoraInicio(),
            h.getHoraFin()
        });
    }
}
}
