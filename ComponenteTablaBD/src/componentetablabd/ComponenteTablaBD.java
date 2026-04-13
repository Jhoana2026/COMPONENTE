/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package componentetablabd;

import java.sql.Connection;
import java.util.List;



/**
 *
 * @author Lenovo
 */
public class ComponenteTablaBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Consultas consulta = new Consultas();

        List<Object> datos = consulta.obtenerColumna(
            "componentebd",
            "productos",
            "nombre"
        );

        for (Object dato : datos) {
            System.out.println(dato);
        }
        
    }
    
}
