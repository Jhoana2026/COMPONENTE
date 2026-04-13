/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentetablabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class Consultas {
    public List<Object> obtenerColumna(String base, String tabla, String columna) {
        List<Object> datos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + base,
                "root",
                ""
            );

            String sql = "SELECT " + columna + " FROM " + tabla;
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos.add(rs.getObject(columna));
            }

        } catch (Exception e) {
            System.out.println("Error en consulta: " + e.getMessage());
        }

        return datos;
    }
}
