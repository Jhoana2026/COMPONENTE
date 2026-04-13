/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentetablabd;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Lenovo
 */
public class JTablaBD extends JScrollPane {

    private JTable tabla;
    private String base;
    private String tablaBD;
    private String columna;

    public JTablaBD() {
        tabla = new JTable();
        setViewportView(tabla);
    }

    public void cargarColumna(String base, String tablaBD, String columna) {
        this.base = base;
        this.tablaBD = tablaBD;
        this.columna = columna;

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn(columna);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + base,
                "root",
                ""
            );

            String sql = "SELECT " + columna + " FROM " + tablaBD;
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Object valor = rs.getObject(columna);
                modelo.addRow(new Object[]{valor});
            }

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setCellRenderer(new ColorRenderer());

        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    class ColorRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            if (value instanceof Number) {
                c.setBackground(Color.RED);
            } else {
                c.setBackground(Color.GREEN);
            }

            return c;
        }
    }
}