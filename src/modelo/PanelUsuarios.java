/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controlador.ControladorUsuario;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import vista.FRMRegistrarse;
import vista.FRMEditarUsuario;

/**
 *
 * @author erick
 */
public class PanelUsuarios extends JPanel{
    private javax.swing.JDesktopPane escritorio;
    private JTable tablaUsuarios;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;
   

    public PanelUsuarios(javax.swing.JDesktopPane escritorio) {
        
        this.escritorio = escritorio;

        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Administración de Usuarios");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        tablaUsuarios = new JTable();
        
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID", "Nombre", "Teléfono", "Correo", "Rol"
            }
        ));

        JScrollPane scroll = new JScrollPane(tablaUsuarios);

        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnNuevo = new JButton("Nuevo");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);

        add(panelBotones, BorderLayout.SOUTH);
        cargarUsuarios();
        
        btnNuevo.addActionListener(e -> nuevoUsuario());

        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnActualizar.addActionListener(e -> actualizarTabla());
        btnEditar.addActionListener(e -> editarUsuario());
    }
    
    private void cargarUsuarios() {

    ControladorUsuario controlador = new ControladorUsuario();

    ArrayList<Usuario> lista = controlador.listarUsuarios();

    DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();

    modelo.setRowCount(0);

        for (Usuario usuario : lista) {

            modelo.addRow(new Object[]{
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getTelefono(),
                usuario.getCorreo(),
                usuario.getRol()
            });

        }
    }
    private void nuevoUsuario() {

        System.out.println("Abriendo formulario");
        
        FRMRegistrarse formulario = new FRMRegistrarse();
        escritorio.add(formulario);

        formulario.setVisible(true);
    }
    
    private void eliminarUsuario() {

        int fila = tablaUsuarios.getSelectedRow();

        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Seleccione un usuario");
            return;
        }

        int id = (int) tablaUsuarios.getValueAt(fila, 0);

        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar este usuario?",
                "Confirmar",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {

            ControladorUsuario controlador = new ControladorUsuario();

            if (controlador.eliminarUsuario(id)) {

                javax.swing.JOptionPane.showMessageDialog(this,
                        "Usuario eliminado");

                cargarUsuarios();

            } else {

                javax.swing.JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar");

            }
        }
    }
    private void actualizarTabla() {

        cargarUsuarios();

        javax.swing.JOptionPane.showMessageDialog(this,
                "Tabla actualizada");
    }
    private void editarUsuario() {

        int fila = tablaUsuarios.getSelectedRow();

        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Seleccione un usuario.");
            return;
        }

        int id = Integer.parseInt(
                tablaUsuarios.getValueAt(fila, 0).toString());

        ControladorUsuario controlador = new ControladorUsuario();

        Usuario usuario = controlador.buscarUsuario(id);

        if (usuario == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No se encontró el usuario.");
            return;
        }

        FRMEditarUsuario formulario = new FRMEditarUsuario(usuario);

        escritorio.add(formulario);

        formulario.setVisible(true);
    }
}
