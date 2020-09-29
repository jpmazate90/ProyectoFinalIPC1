
package InterfazGrafica;

import Datos.Cliente;
import Datos.ControlNumeroFacturas;
import Datos.EnsamblarMueble;
import Datos.Factura;
import Datos.Mueble;
import Datos.Usuario;
import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import Logica.Validaciones;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jpmazate
 */
public class RegistrarCompra extends javax.swing.JDialog {
    //atributos de la clase
    private SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private CargarArchivo cargador = new CargarArchivo();
    private GuardarArchivo guardador = new GuardarArchivo();
    private Validaciones validador = new Validaciones();
    private Date fecha;
    // constructor
    public RegistrarCompra(java.awt.Frame parent, String usuario) {
        super(parent, true);
        initComponents();
        fecha = new Date();
        asignarFecha();
        asignarNumeroFactura();
        lblNombre.setVisible(false);
        lblDireccion.setVisible(false);
        textoNombre.setVisible(false);
        textoDireccion.setVisible(false);
        botonRegistrar.setVisible(false);
        textoMueble.setEditable(false);
        textoTotal.setEditable(false);
        lblUsuario.setText(usuario);
        
    }
    
    // asigna la fecha
    public void asignarFecha(){
        String fechaActual = formato.format(fecha);
        lblFecha.setText(fechaActual);
    }
    // asigna el numero de fecha
    public void asignarNumeroFactura(){
        try {
            ControlNumeroFacturas controladorFacturas = cargador.cargarContadorFacturas();
            lblFactura.setText(Integer.toString(controladorFacturas.getNumeroFactura()));
            controladorFacturas.setNumeroFactura(controladorFacturas.getNumeroFactura()+1);
            guardador.guardarContadorFactura(controladorFacturas);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // busca el cliente
    public void buscarCliente(){
        Cliente cliente = cargador.cargarCliente(Integer.parseInt(textoNIT.getText()));
        if(cliente==null){// si no existe dice que ingrese datos
            JOptionPane.showMessageDialog(null, "No existe el NIT en el sistema, por favor ingrese sus datos");
            textoNombre.setEnabled(true);
            textoDireccion.setEnabled(true);
            botonRegistrar.setVisible(true);
            textoNombre.setText("");
            textoDireccion.setText("");
// si no muestra los que ya existen
        }else{
            textoNombre.setText(cliente.getNombre());
            textoDireccion.setText(cliente.getDireccion());
            textoNombre.setEnabled(false);
            textoDireccion.setEnabled(false);
        }
        
        lblNombre.setVisible(true);
        lblDireccion.setVisible(true);
        textoNombre.setVisible(true);
        textoDireccion.setVisible(true);
    }
    // busca al muebble
    public void buscarMueble(){
        try{
        EnsamblarMueble  mueble = cargador.cargarMueblePorIdentificador(Integer.parseInt(textoIdentificador.getText()));
        if(mueble==null){
            // mira si cumple el identificador
            JOptionPane.showMessageDialog(null,"No existe un mueble con ese identificador, o ya esta vendido");
            limpiarTextosMueble();
        }else if(mueble.isEstadoMueble()==false){
          JOptionPane.showMessageDialog(null, "Este mueble ya esta vendido");
        }else{
            textoMueble.setText(mueble.getNombre());
            textoTotal.setText(Double.toString(mueble.getPrecioVenta()));
        }
        }catch(NumberFormatException e){
                      JOptionPane.showMessageDialog(null, "El identificador no es correcto");

        }
            
    }
    // registra la compra
    public void registrarCompra(){
        String[] datos=asignarArregloDatos();
        boolean sePuede=validador.validarDatosCompra(datos);
        if(sePuede==true){
            
            registrarFactura();
            JOptionPane.showMessageDialog(null,"Se puede registrar");
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null,"NO Se puede registrar");
        }
        
    }
    // registra la factura
    public void registrarFactura(){
        String[] datos = asignarArregloDatos();
        EnsamblarMueble muebleAVender;
        // mira si se puede crear
        muebleAVender = cargador.cargarMueblePorIdentificador(Integer.parseInt(textoIdentificador.getText()));
        boolean sePuede = validador.registarFactura(datos, lblFecha.getText(), lblFactura.getText(), lblUsuario.getText(), muebleAVender.getCosto());
        if(sePuede==true){
            Usuario usuario;
            // crea la factura
            Mueble mueble;
            usuario=cargador.cargarUsuario(lblUsuario.getText());
            mueble = cargador.cargarMueble(textoMueble.getText());
            mueble.setVecesVendido(mueble.getVecesVendido()+1);
            usuario.setHistorialVentas(usuario.getHistorialVentas()+1);
            muebleAVender.setEstadoMueble(false);
            guardador.guardarMueble(mueble);
            guardador.guardarUsuario(usuario);
            guardador.guardarMuebleEnsamblado(muebleAVender);
        }
        
    }
    // registra al cliente
    public void registrarCliente(){
        
        validador.registrarCliente(Integer.parseInt(textoNIT.getText()), textoNombre.getText(), textoDireccion.getText());
        
    }
    // crea un arreglo de datos
    public String[] asignarArregloDatos(){
        String[] datos = new String[6];
        datos[0]=textoNIT.getText();
        datos[1]=textoNombre.getText();
        datos[2]=textoDireccion.getText();
        datos[3]=textoIdentificador.getText();
        datos[4]=textoMueble.getText();
        datos[5]=textoTotal.getText();
        
        return datos;
    }
    // limpia el texto
    public void limpiarTextosMueble(){
        
        textoIdentificador.setText("");
        textoMueble.setText("");
        textoTotal.setText("");
    }
    //limpia texto
    public void limpiarTextosNombre(){
        textoDireccion.setText("");
        textoNombre.setText("");
        textoNIT.setText("");

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoNIT = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        textoDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textoIdentificador = new javax.swing.JTextField();
        textoMueble = new javax.swing.JTextField();
        textoTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblFactura = new javax.swing.JLabel();
        botonRegistrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REGISTRAR COMPRA");

        jLabel2.setText("NIT:");

        textoNIT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNITKeyTyped(evt);
            }
        });

        lblNombre.setText("Nombre:");

        lblDireccion.setText("Direccion:");

        jLabel5.setText("Fecha Compra:");

        jLabel6.setText("Numero Factura:");

        jLabel7.setText("Identificador Mueble:");

        jLabel8.setText("Mueble:");

        jLabel9.setText("Total:");

        textoIdentificador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoIdentificadorKeyTyped(evt);
            }
        });

        jButton1.setText("Registrar Compra");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar Cliente ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Ingrese el nit, luego busque si existe el cliente en el boton:\"Buscar Cliente\".");

        jButton3.setText("Buscar Mueble");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setText("Ingrese el identificador del mueble y luego busquelo con el boton:\"Buscar Mueble\".");

        botonRegistrar.setText("RegistrarCliente");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        jLabel3.setText("Usuario:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textoIdentificador)
                                            .addComponent(textoMueble)
                                            .addComponent(textoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(jButton3))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton1))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(lblNombre))
                                                .addGap(49, 49, 49)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(textoNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblDireccion)
                                                .addGap(40, 40, 40)
                                                .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(botonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                .addGap(6, 6, 6))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(363, 363, 363)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textoNIT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jLabel3)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDireccion)
                            .addComponent(botonRegistrar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(lblFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)))
                .addGap(29, 29, 29)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(textoMueble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(58, 58, 58))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoNITKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNITKeyTyped
        
    }//GEN-LAST:event_textoNITKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        buscarCliente();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void textoIdentificadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoIdentificadorKeyTyped
        
    }//GEN-LAST:event_textoIdentificadorKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        buscarMueble();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        registrarCompra();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        registrarCliente();
    }//GEN-LAST:event_botonRegistrarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField textoDireccion;
    private javax.swing.JTextField textoIdentificador;
    private javax.swing.JTextField textoMueble;
    private javax.swing.JTextField textoNIT;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoTotal;
    // End of variables declaration//GEN-END:variables
}
