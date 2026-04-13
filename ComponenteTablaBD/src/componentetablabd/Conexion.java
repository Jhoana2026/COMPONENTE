/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentetablabd;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Lenovo
 */
public class Conexion {
    public Connection conectar() {
        Connection conexion = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/componentebd",
                "root",
                ""
            );
            
            System.out.println("Conexion exitosa desde la clase Conexion");
            
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        
        return conexion;
    }
}
