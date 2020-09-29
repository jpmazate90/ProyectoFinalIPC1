package InterfazGrafica;

import Datos.Usuario;
import Logica.CargarArchivo;
import Logica.Validaciones;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class EliminarUsuario extends javax.swing.JDialog {
    //atributos de la clase
    String usuario;
    private CargarArchivo cargador = new CargarArchivo();
    private Validaciones validador = new Validaciones();
    private JDesktopPane escritorio;
// constructor
    public EliminarUsuario(java.awt.Frame parent, String usuario, JDesktopPane escritorio) {
        super(parent, true);
        initComponents();
        this.usuario=usuario;
        this.escritorio=escritorio;
        ocultarDatos();
    }
    // busca al usuario
    public void buscarUsuario(){
        Usuario usuario = null;
        usuario = cargador.cargarUsuario(textUsuario.getText());
        if(usuario == null){
            // muestra si no existe
            JOptionPane.showMessageDialog(null,"No existe el usuario con el nombre: "+textUsuario.getText());
        }else{
            textoArea.setText(usuario.getArea());
            textoPassword.setText(usuario.getPassword());
            mostrarDatos();
        }
    }
    // eliina a un usuario
    public void eliminarUsuario(){
        int opcion=JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar al usuario? ya no se podra restaurar");
        if(opcion==JOptionPane.YES_OPTION){
            desactivarUsuario();
            if(this.usuario.equals(textUsuario.getText())){
                limpiar();
            }
            this.setVisible(false);
        }else if(opcion==JOptionPane.NO_OPTION){
            this.setVisible(false);
        }else{
            
        }
    }
    // desactiva un usuario
    public void desactivarUsuario(){
        Usuario usuario = null;
        usuario = cargador.cargarUsuario(textUsuario.getText());
        boolean sePudo = validador.desactivarUsuario(usuario);
        if(sePudo==true){
            JOptionPane.showMessageDialog(null, "Se ha eliminado el usuario del sistema");
            if(textUsuario.equals(this.usuario)){
                limpiar();
            }
        }else{
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    }
    public void limpiar(){
        this.escritorio.removeAll();
        this.escritorio.repaint();
    }
    // oculta los datos
    public void ocultarDatos(){
        lblArea.setVisible(false);
        lblPassword.setVisible(false);
        lblNota.setVisible(false);
        botonEliminar.setVisible(false);
        textoArea.setVisible(false);
        textoPassword.setVisible(false);
    }
    
    // muestra los datos
    public void mostrarDatos(){
        lblArea.setVisible(true);
        lblPassword.setVisible(true);
        lblNota.setVisible(true);
        botonEliminar.setVisible(true);
        textoArea.setVisible(true);
        textoPassword.setVisible(true);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblPassword = new javax.swing.JLabel();
        lblArea = new javax.swing.JLabel();
        botonEliminar = new javax.swing.JButton();
        lblNota = new javax.swing.JLabel();
        textoPassword = new javax.swing.JLabel();
        textoArea = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ELIMINAR USUARIO");

        jLabel2.setText("NOMBRE USUARIO:");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblPassword.setText("Contraseña:");

        lblArea.setText("Area:");

        botonEliminar.setText("Eliminar Usuario");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        lblNota.setText("NOTA: No se podra restaurar el usuario despues.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lblPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textoPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textUsuario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textoArea, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 96, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonEliminar)
                            .addComponent(lblNota)
                            .addComponent(lblArea))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(textoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblNota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEliminar)
                .addGap(105, 105, 105))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarUsuario();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        eliminarUsuario();
    }//GEN-LAST:event_botonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField textUsuario;
    private javax.swing.JLabel textoArea;
    private javax.swing.JLabel textoPassword;
    // End of variables declaration//GEN-END:variables
}
