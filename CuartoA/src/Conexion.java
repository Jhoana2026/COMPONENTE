/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author Lenovo
 */
public class Conexion {
    Connection Conectar;
    public Connection Conectar(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conectar=DriverManager.getConnection("jdbc:mysql://localhost:3306/cuarto","root","");
            //JOptionPane.showMessageDialog(null, "ok estas conectado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return Conectar;
    }
}
