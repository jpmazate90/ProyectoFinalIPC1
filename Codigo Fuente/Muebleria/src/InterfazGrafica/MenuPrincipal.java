
package InterfazGrafica;

import AcercaDe.AcercaDe;
import Datos.Usuario;
import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author jpmazate
 */
public class MenuPrincipal extends javax.swing.JFrame {
    //atributos del menu
    private CargarArchivo cargador = new CargarArchivo();
    private GuardarArchivo guardador = new GuardarArchivo();

    public MenuPrincipal() {
        initComponents();
        verificarArchivos();
    }
    // manda al frame correspondiente
    public void agregarFrame(int a, String usuario){
        switch(a){
            case 1: Fabrica fabrica = new Fabrica(this, usuario); 
                    limpiar();
                    this.escritorio.add(fabrica);
                    fabrica.show();
                    break;
            case 2: PuntoVenta puntoVenta = new PuntoVenta(this, usuario); 
                    limpiar();
                    this.escritorio.add(puntoVenta);
                    puntoVenta.show();
                    break;
            case 3: AdministracionFinanciera admin = new AdministracionFinanciera(this, usuario, this.escritorio); 
                    limpiar();
                    this.escritorio.add(admin);
                    admin.show();
                    break;
        }
        
    }
    // verifica archivos si es la primera vez que se utiliza
    public void verificarArchivos(){
        Usuario usuario;
        String usuarioDefault = "usuario";
        String password = "password";
        int tipo = 3;
        boolean esPrimeraVez;
        esPrimeraVez= cargador.cargarPrimeraVez();
        if(esPrimeraVez==true){
            // si es primera vez crea un usuario default
            usuario = new Usuario(usuarioDefault, password, tipo);
            guardador.guardarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Hemos detectado que es la primera vez que entras a la aplicacion\n el primer usuario administrativo se creo automaticamente\n"
                    + "Usuario: "+usuarioDefault+" \n Contraseña: "+password);
        }
    }
    // limpia la pantalla
    public void limpiar(){
        this.escritorio.removeAll();
        this.escritorio.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        inicioSesion = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MUEBLERIA");

        fileMenu.setMnemonic('f');
        fileMenu.setText("Iniciar Sesion");

        inicioSesion.setMnemonic('x');
        inicioSesion.setText("IniciarSesion");
        inicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioSesionActionPerformed(evt);
            }
        });
        fileMenu.add(inicioSesion);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Cerrar Sesion");

        jMenuItem1.setText("Cerrar Sesion Actual");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem1);

        menuBar.add(editMenu);

        jMenu3.setText("Acerca De");

        jMenuItem2.setText("Acerca De La Aplicacion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioSesionActionPerformed
        // inicia sesion
        InicioSesion inicio = new InicioSesion(this);
        this.escritorio.add(inicio);
        inicio.show();
    }//GEN-LAST:event_inicioSesionActionPerformed

    
    // guarda la informacion de la lista
    public void guardarInformacionLista(CargaDatos1 carga){
        String[] componentesLista= new String[carga.getModeloLista().getSize()];
        int tamañoLista=carga.getModeloLista().getSize();
        for(int i=0;i<carga.getModeloLista().getSize();i++){
            componentesLista[i]=(String) carga.getModeloLista().getElementAt(i);
        }
        // abre un archivo
        File archivoAGuardar= new File("controlReportes.jp");
        for(int j=0;j<componentesLista.length;j++){
            // guarda datos de la lista
            try(FileWriter out = new FileWriter(archivoAGuardar,true); BufferedWriter archivoSalida = new BufferedWriter(out);){
                archivoSalida.write(componentesLista[j]);
                archivoSalida.newLine();
                archivoSalida.flush();
            }catch (IOException ex) {
                Logger.getLogger(CargaDatos1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        limpiar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AcercaDe.textoProgramador();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem inicioSesion;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
