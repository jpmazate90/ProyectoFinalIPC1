
package InterfazGrafica;

import Datos.Usuario;
import Logica.CargarArchivo;
import Logica.Validaciones;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jpmazate
 */
public class InicioSesion extends javax.swing.JInternalFrame {

    //atributos de la clase
    public static Validaciones validador = new Validaciones();
    public static CargarArchivo cargador = new CargarArchivo();
    private MenuPrincipal menu;
    //constructor
    public InicioSesion(MenuPrincipal a) {
        
        initComponents();
        this.menu=a;
        mensajeError.setVisible(false);
        
    }
// valida el usuario
    public void validarUsuario(){
       String usuario = nombreUsuario.getText();
       String password = passwordUsuario.getText();
       boolean puedeEntrar;
       puedeEntrar = validador.verificarUsuario(usuario, password);
       // si cumple entra
       if(puedeEntrar==false){
           mensajeError.setVisible(true);
           nombreUsuario.setText("");
           passwordUsuario.setText("");
       }else{// si no muestra
           JOptionPane.showMessageDialog(this, "Funciono");
           mandarAlArea(usuario);
       }
       
    }
    // manda al area pertinente
    public void mandarAlArea(String nombre){
        Usuario usuario = cargador.cargarUsuario(nombre);
        menu.agregarFrame(usuario.getTipo(), usuario.getNombre());
        
    }
    // cierra si es  necesario
    public void cerrar() { 
        int salir= JOptionPane.showConfirmDialog(this,"¿Esta seguro que desea cerrar la aplicación?","Advertencia",JOptionPane.YES_NO_OPTION);
        if (salir==JOptionPane.YES_OPTION){ 
            JOptionPane.showMessageDialog(null,"Gracias por participar","Gracias",JOptionPane.INFORMATION_MESSAGE); 
            System.exit(0);
        }else { 
            if(salir==JOptionPane.NO_OPTION){ 
                this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE); 
            } 
        } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombreUsuario = new javax.swing.JTextField();
        passwordUsuario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        mensajeError = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jLabel1.setText("Ingresa los datos del usuario");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jButton1.setText("Iniciar Sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mensajeError.setText("No esta la informacion correcta, ingresa los datos de nuevo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mensajeError)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(42, 42, 42)
                                    .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addComponent(passwordUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(mensajeError)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        validarUsuario();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel mensajeError;
    private javax.swing.JTextField nombreUsuario;
    private javax.swing.JTextField passwordUsuario;
    // End of variables declaration//GEN-END:variables
}
