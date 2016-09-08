package Forms;

import java.sql.*;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

public class Biblioteca_Datos extends javax.swing.JFrame {

    Scanner leer = new Scanner(System.in);
    Connection conexion = null;
    Statement sentencia = null;
    String cadena = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:BD_Biblioteca.sqlite";
    DefaultTableModel modelo = new DefaultTableModel();
    ResultSet rs = null;
    
    public Biblioteca_Datos() {
        initComponents();
        tabla.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnbuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbFiltro = new javax.swing.JComboBox<>();
        txtFiltro = new javax.swing.JTextField();

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar por:");

        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Titulo", "Precio", "Autor" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnbuscar)
                    .addComponent(jLabel1)
                    .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed

       Limpiar_Tabla();
       
        try {
            Class.forName(cadena);
            conexion = DriverManager.getConnection(url);
            conexion.setAutoCommit(false);
            sentencia = conexion.createStatement();

            rs = null;
            
            Object[] fila = new Object[4];

            if ("".equals(txtFiltro.getText())) {
                rs = sentencia.executeQuery("SELECT * FROM Tabla_Libros");

                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();
                for (int i = 1; i <= 4; i++) {
                    modelo.addColumn(rsMd.getColumnLabel(i));
                }
                while (rs.next()) {
                    for (int i = 0; i < 4; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(fila);
                }
            } else {
                if (cbFiltro.getSelectedItem() == "Código") {
                    rs = sentencia.executeQuery("SELECT * FROM Tabla_Libros WHERE codigo =" + txtFiltro.getText());
                }
                if (cbFiltro.getSelectedItem() == "Titulo") {
                    rs = sentencia.executeQuery("SELECT * FROM Tabla_Libros WHERE titulo ='" + txtFiltro.getText() + "'");
                }
                if (cbFiltro.getSelectedItem() == "Precio") {
                    rs = sentencia.executeQuery("SELECT * FROM Tabla_Libros WHERE precio ='" + txtFiltro.getText() + "'");
                }
                if (cbFiltro.getSelectedItem() == "Autor") {
                    rs = sentencia.executeQuery("SELECT * FROM Tabla_Libros WHERE autor ='" + txtFiltro.getText() + "'");
                }

                ResultSetMetaData rsMd = rs.getMetaData();
                
                int cantidadColumnas = rsMd.getColumnCount();
                
                for (int i = 1; i <= 4; i++) {
                    modelo.addColumn(rsMd.getColumnLabel(i));
                }
                while (rs.next()) {
                    for (int i = 0; i < 4; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(fila);
                }

                rs.close();
                sentencia.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
        }

    }//GEN-LAST:event_btnbuscarActionPerformed

    public void Limpiar_Tabla()
    {
       modelo.setColumnCount(0);
       modelo.setRowCount(0);
    }
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteca_Datos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox<String> cbFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
