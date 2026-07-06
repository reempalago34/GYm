/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import controlador.ControladorEntrenador;
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
import vista.FRMEditarEntrenador;
import vista.FRMRegistrarEntrenador;

/**
 *
 * @author erick
 */
public class PanelEntrenador extends javax.swing.JPanel {
    private JTable tablaEntrenadores;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    private JDesktopPane escritorio;

    public PanelEntrenador(JDesktopPane escritorio) {

        this.escritorio = escritorio;

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Administración de Entrenadores");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        tablaEntrenadores = new JTable();

        tablaEntrenadores.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID",
                    "Nombre",
                    "Teléfono",
                    "Correo",
                    "Especialidad"
                }
        ));

        JScrollPane scroll = new JScrollPane(tablaEntrenadores);

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

        cargarEntrenadores();
        
        btnNuevo.addActionListener(e -> {

            FRMRegistrarEntrenador frm = new FRMRegistrarEntrenador();

            escritorio.add(frm);

            frm.setVisible(true);

        });
        btnEditar.addActionListener(e -> {

            int fila = tablaEntrenadores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this,
                        "Seleccione un entrenador.");

                return;
            }

            int id = (int) tablaEntrenadores.getValueAt(fila, 0);

            ControladorEntrenador controlador = new ControladorEntrenador();

            Entrenador entrenador = controlador.buscarEntrenador(id);

            FRMEditarEntrenador frm = new FRMEditarEntrenador(entrenador);

            escritorio.add(frm);

            frm.setVisible(true);

        });
        
        btnEliminar.addActionListener(e -> {

            int fila = tablaEntrenadores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(this,
                        "Seleccione un entrenador.");

                return;
            }

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar este entrenador?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                int id = (int) tablaEntrenadores.getValueAt(fila, 0);

                ControladorEntrenador controlador = new ControladorEntrenador();

                if (controlador.eliminarEntrenador(id)) {

                    JOptionPane.showMessageDialog(this,
                            "Entrenador eliminado correctamente.");

                    cargarEntrenadores();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No se pudo eliminar el entrenador.");
                }
            }

        });
        btnActualizar.addActionListener(e -> {
            cargarEntrenadores();
        });
    }
    public void cargarEntrenadores() {

        ControladorEntrenador controlador = new ControladorEntrenador();

        DefaultTableModel modelo =
                (DefaultTableModel) tablaEntrenadores.getModel();

        modelo.setRowCount(0);

        for (Entrenador e : controlador.listarEntrenadores()) {

            modelo.addRow(new Object[]{
                e.getIdEntrenador(),
                e.getNombre(),
                e.getTelefono(),
                e.getCorreo(),
                e.getEspecialidad()
            });

        }

    }
    
}
