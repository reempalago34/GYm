/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import controlador.ControladorMembresia;
import vista.FRMRegistrarMembresia;
import javax.swing.JOptionPane;
import vista.FRMEditarMembresia;
/**
 *
 * @author erick
 */
public class PanelMembresias extends JPanel{
    private JTable tablaMembresias;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    private JDesktopPane escritorio;

    public PanelMembresias(JDesktopPane escritorio) {

        this.escritorio = escritorio;

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Administración de Membresías");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        tablaMembresias = new JTable();

        tablaMembresias.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID", "Nombre", "Precio", "Duración"
            }
        ));

        JScrollPane scroll = new JScrollPane(tablaMembresias);

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
        
        btnNuevo.addActionListener(e -> {
            FRMRegistrarMembresia frm = new FRMRegistrarMembresia();
            escritorio.add(frm);
            frm.setVisible(true);
        });
        
        btnActualizar.addActionListener(e -> {
            cargarMembresias();
        });
        btnEditar.addActionListener(e -> {

            int fila = tablaMembresias.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(this,
                        "Seleccione una membresía.");
                return;
            }

            int id = (int) tablaMembresias.getValueAt(fila, 0);

            ControladorMembresia controlador = new ControladorMembresia();

            Membresia membresia = controlador.buscarMembresia(id);

            FRMEditarMembresia frm = new FRMEditarMembresia(membresia);

            escritorio.add(frm);
            frm.setVisible(true);

        });
        btnEliminar.addActionListener(e -> {

            int fila = tablaMembresias.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(this,
                        "Seleccione una membresía.");
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar esta membresía?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                int id = (int) tablaMembresias.getValueAt(fila, 0);

                ControladorMembresia controlador = new ControladorMembresia();

                if (controlador.eliminarMembresia(id)) {

                    JOptionPane.showMessageDialog(this,
                            "Membresía eliminada correctamente.");

                    cargarMembresias();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No se pudo eliminar la membresía.");

                }
            }

        });
        cargarMembresias();

    }    
    public void cargarMembresias() {

        ControladorMembresia controlador = new ControladorMembresia();

        DefaultTableModel modelo = (DefaultTableModel) tablaMembresias.getModel();
        modelo.setRowCount(0);

        for (Membresia m : controlador.listarMembresias()) {
            modelo.addRow(new Object[]{
                m.getIdMembresia(),
                m.getNombre(),
                m.getPrecio(),
                m.getDuracion()
            });
        }
    }
    
    
}
