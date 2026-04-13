
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
public class Estudiante extends javax.swing.JFrame {

    DefaultTableModel model;
    /**
     * Creates new form Estudiante
     */
    public Estudiante() {
        initComponents();
        consumirSelectEstudentDao();
        //consumirselectEstudentDao();
        SelectRowEstudent();
       
    }
    
    
    //CAMBIOS
    
    public void consumidorDaoInsertar() {

    Estudinate estudiante = new Estudinate(
            jtxtCedula.getText(),
            jtxtNombre.getText(),
            jtxtApellido.getText(),
            jtxtDireccion.getText(),
            jtxtTelefono.getText()
    );

    DAO dao = new DAO();
    dao.insertEstudentDAO(estudiante);
}
    
   
    public void consumirSelectEstudentDao() {
        try {
            DAO dao = new DAO ();
            List <Estudinate> lista=dao.selectStudentDao();
            String titulos[] = {"CEDULA", "NOMBRE", "APELLIDO", "DIRECCION", "TELEFONO"};
            model = new DefaultTableModel(null , titulos);
            for (Estudinate est:lista){
                Object[] registro = {est.getEstCedula(),
                                    est.getEstNombre(),
                                    est.getEstApellido(),
                                    est.getEstDireccion(),
                                    est.getEstTelefono()

                };
                model.addRow(registro);
            }
            jtblEstudiantes.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se inserto comuniquese con el administrador ");
        }
     
    }
    
    
    
    public void consumidorDaoDelete() {
        if (JOptionPane.showConfirmDialog(
                this,
                "Estas seguro de borrar este estudiante",
                "Borrar estudiante",
                JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {

            DAO dao = new DAO();
            dao.deleteEstudentDAO(jtxtCedula.getText());
            consumirSelectEstudentDao();
        }
    }
    
    public void consumidorDaoUpdate() {

    Estudinate estudiante = new Estudinate(
            jtxtCedula.getText(),
            jtxtNombre.getText(),
            jtxtApellido.getText(),
            jtxtDireccion.getText(),
            jtxtTelefono.getText()
    );

    DAO dao = new DAO();
    dao.updateEstudentDAO(estudiante);
    consumirSelectEstudentDao();
}
    
    /*
    public void selectEstudent()
    {
        try {
            String titulos[] = {"Cedula", "Nombre","Apellido", "Direccion", "Telefono"};
            String registros[] = new String[5];
            model = new DefaultTableModel(null, titulos);
            Conexion cn = new Conexion();
            Connection cc= cn.Conectar();
            Statement psd=cc.createStatement();
            String sql="select * from estudiantes";
            ResultSet rs=psd.executeQuery(sql);
            while(rs.next())
            {
                registros[0]=rs.getString("estCedula");
                registros[1]=rs.getString("estNombre");
                registros[2]=rs.getString("estApellido");
                registros[3]=rs.getString("estDireccion");
                registros[4]=rs.getString("estTelefono");
                model.addRow(registros);
            }
            
            
            jtblEstudiantes.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    */
    // CORRECCIONES CON DAO (A MEDIDA QUE SE CORRIGE EN DAO ESTO SE ARREGLA)
    
                //SEGUNDO CAMBIO
    /*
   public void selectStudent() {
        try {
            DAO dao = new DAO();
            List<Estudinate> listaEstudiantes = dao.selectStudentDao();
            String[] columnas = {"Cedula", "Nombre", "Apellido", "Direccion", "Telefono"};
            String[] filas = new String[5];
            model = new DefaultTableModel(null, columnas);
            for (Estudinate estudiante : listaEstudiantes) {
                filas[0] = estudiante.getEstCedula();
                filas[1] = estudiante.getEstNombre();
                filas[2] = estudiante.getEstApellido();
                filas[3] = estudiante.getEstDireccion();
                filas[4] = estudiante.getEstTelefono();
                model.addRow(filas);
            }
            jtblEstudiantes.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void insertEstudent(){
        try {
            Conexion cn = new Conexion();
            Connection cc= cn.Conectar();
            String sql = "insert into estudiantes values(?,?,?,?,?)";
            PreparedStatement psd=cc.prepareStatement(sql);
            psd.setString(1, jtxtCedula.getText());   //ctrl + espacio
            psd.setString(2, jtxtNombre.getText());
            psd.setString(3, jtxtApellido.getText());
            psd.setString(4, jtxtDireccion.getText());
            psd.setString(5, jtxtTelefono.getText());
            int res=psd.executeUpdate();
            if(res>0)
            {
                JOptionPane.showMessageDialog(null, "Se inserto correctamente");
                selectStudent();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se inserto, comuniquese con el administrador");
        }
        
    }
    
    
    public void deleteEstudent(){
        if(JOptionPane.showConfirmDialog(this, "Esta seguro de borrar?", "Borrar estudiante", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        try {
            Conexion cn = new Conexion();
            Connection cc= cn.Conectar();
            String sql = "delete from estudiantes where estCedula='"+jtxtCedula.getText()+"'";
            PreparedStatement psd= cc.prepareStatement(sql);
            int res=psd.executeUpdate();
            if(res>0)
            {
                JOptionPane.showMessageDialog(null, "Se borro correctamente");
                selectStudent();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
    
    
    public void updateEstudent(){
        try {
            Conexion cn = new Conexion();
            Connection cc= cn.Conectar();
            String sql = "update estudiantes set "
                         + "estNombre = '" + jtxtNombre.getText() + "', "
                         + "estApellido = '" + jtxtApellido.getText() + "', "
                         + "estDireccion = '" + jtxtDireccion.getText() + "', "
                         + "estTelefono = '" + jtxtTelefono.getText() + "' "
                         + "where estCedula = '" + jtxtCedula.getText() + "'";
            PreparedStatement psd= cc.prepareStatement(sql);
            int res=psd.executeUpdate();
            if(res>0)
            {
                JOptionPane.showMessageDialog(null, "Se actualizo correctamente");
                selectStudent();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
   */
    
    public void SelectRowEstudent(){
        jtblEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtblEstudiantes.getSelectedRow()!=-1){
                    int fila =jtblEstudiantes.getSelectedRow();
                    jtxtCedula.setText(jtblEstudiantes.getValueAt(fila, 0).toString());
                    jtxtNombre.setText(jtblEstudiantes.getValueAt(fila, 1).toString());
                    jtxtApellido.setText(jtblEstudiantes.getValueAt(fila, 2).toString());
                    jtxtDireccion.setText(jtblEstudiantes.getValueAt(fila, 3).toString());
                    jtxtTelefono.setText(jtblEstudiantes.getValueAt(fila, 4).toString());
            }
            }
        });
    }
    
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtxtCedula = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellido = new javax.swing.JTextField();
        jtxtDireccion = new javax.swing.JTextField();
        jtxtTelefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnGuardar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnBorrar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblEstudiantes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Ingrese cedula");

        jLabel2.setText("Ingrese nombre");

        jLabel3.setText("Ingrese apellido");

        jLabel4.setText("Ingrese direccion");

        jLabel5.setText("Ingrese telefono");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtxtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtxtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbtnNuevo.setText("Nuevo");

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnEditar.setText("Editar");
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });

        jbtnBorrar.setText("Borrar");
        jbtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnCancelar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jbtnBorrar)
                        .addComponent(jbtnEditar)
                        .addComponent(jbtnGuardar)
                        .addComponent(jbtnNuevo)))
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jbtnNuevo)
                .addGap(34, 34, 34)
                .addComponent(jbtnGuardar)
                .addGap(27, 27, 27)
                .addComponent(jbtnEditar)
                .addGap(26, 26, 26)
                .addComponent(jbtnBorrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jbtnCancelar)
                .addGap(24, 24, 24))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtblEstudiantes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        consumidorDaoInsertar();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrarActionPerformed
        // TODO add your handling code here:
        consumidorDaoDelete();
    }//GEN-LAST:event_jbtnBorrarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        // TODO add your handling code here:
        consumidorDaoUpdate();
    }//GEN-LAST:event_jbtnEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnBorrar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JTable jtblEstudiantes;
    private javax.swing.JTextField jtxtApellido;
    private javax.swing.JTextField jtxtCedula;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtTelefono;
    // End of variables declaration//GEN-END:variables
}



