
package InterfazGrafica;

import Datos.ControlNumeroDevoluciones;
import Datos.Devolucion;
import Datos.EnsamblarMueble;
import Datos.Factura;
import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RegistroDevolucion extends javax.swing.JDialog {
// atributos de la clase
    private CargarArchivo cargador = new CargarArchivo();
    private GuardarArchivo guardador = new GuardarArchivo();
    // constructor
    public RegistroDevolucion(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        ocultarDatos();
    }
    // busca la factura para ver si se puede hacer devolucion
    public void buscarFactura(){
        Factura factura = cargador.cargarFactura(Integer.parseInt(textoNumeroFactura.getText()));
        if(factura==null){
            JOptionPane.showMessageDialog(null, "No existe esa factura");
            textoNumeroFactura.setText("");
        }else{
            agregarDatos(factura);
            mostrarDatos();
        }
        
    }
    // registra la devolucion
    public void registrarDevolucion(){
        Date fechaActual = new Date();
        Factura factura = cargador.cargarFactura(Integer.parseInt(textoNumeroFactura.getText()));
        if(fechaActual.compareTo(factura.getFechaMaximaDevolucion())<0 && factura.isSePuedeDevolver()==true){
            guardarDevolucion(factura);
            factura.setSePuedeDevolver(false);
            guardador.guardarFactura(factura);
        }else{
                // si se paso la fecha dice que no se puede 
                if(factura.isSePuedeDevolver()==false){
                    JOptionPane.showMessageDialog(null, "Ya se ha hecho una devolucion con el numero de factura: "+factura.getNumeroFactura());
                }else{
                    JOptionPane.showMessageDialog(null, "No se puede hacer la devolucion, la fecha maxima ha llegado");
                }
        }
    }
    // guarda la devolucion
    public void guardarDevolucion(Factura factura){
        Date fechaActual = new Date();
        ControlNumeroDevoluciones control;
        EnsamblarMueble mueble = cargador.cargarMuebleVendido(Integer.parseInt(factura.getIdentificador()));
        Devolucion devolucion = new Devolucion(fechaActual, factura.getFechaCompra(), factura.getNombreCliente(),
                                               factura.getNombreMueble(), factura.getTotal(),mueble.getCosto(),mueble.getIdentificador(), factura.getNumeroFactura());
        // dice que se guardo
        try {
            control = cargador.cargarContadorDevoluciones();
            guardador.guardarDevolucion(devolucion, control.getNumeroDevolucion());
            control.setNumeroDevolucion(control.getNumeroDevolucion()+1);
            guardador.guardarControladorDevoluciones(control);
            JOptionPane.showMessageDialog(null, "Se hizo la devolucion correctamente");
            this.setVisible(false);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // agrega datos a las casillas
    public void agregarDatos(Factura factura){
        lblNombre.setText(factura.getNombreCliente());
        lblNIT.setText(Integer.toString(factura.getNIT()));
        lblMueble.setText(factura.getNombreMueble());
        lblTotal.setText(Double.toString(factura.getTotal()));
    }
    //oculta los datos
    public void ocultarDatos(){
        lbMueble.setVisible(false);
        lbNIT.setVisible(false);
        lbNombre.setVisible(false);
        lbTotal.setVisible(false);
        lblMueble.setVisible(false);
        lblNIT.setVisible(false);
        lblNombre.setVisible(false);
        lblTotal.setVisible(false);
        botonDevolucion.setVisible(false);
        
    }
    // muestra los datos
    public void mostrarDatos(){
        lbMueble.setVisible(true);
        lbNIT.setVisible(true);
        lbNombre.setVisible(true);
        lbTotal.setVisible(true);
        lblMueble.setVisible(true);
        lblNIT.setVisible(true);
        lblNombre.setVisible(true);
        lblTotal.setVisible(true);
        botonDevolucion.setVisible(true);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoNumeroFactura = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lbNombre = new javax.swing.JLabel();
        lbNIT = new javax.swing.JLabel();
        lbMueble = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblNIT = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblMueble = new javax.swing.JLabel();
        botonDevolucion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REGISTRO DEVOLUCION");

        jLabel2.setText("Numero factura:");

        jButton1.setText("Buscar Factura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbNombre.setText("Nombre:");

        lbNIT.setText("NIT:");

        lbMueble.setText("Mueble Comprado:");

        lbTotal.setText("Total:");

        botonDevolucion.setText("Registrar Devolucion");
        botonDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDevolucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(44, 44, 44))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(32, 32, 32)
                                        .addComponent(textoNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lbNombre)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(lbNIT)
                                                .addGap(120, 120, 120)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(30, 30, 30)))
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMueble)
                            .addComponent(lbTotal))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMueble, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonDevolucion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textoNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(29, 29, 29)
                        .addComponent(lbNombre))
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbNIT)
                            .addComponent(lblNIT, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbMueble)
                            .addComponent(lblMueble, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lbTotal))
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonDevolucion)
                        .addGap(13, 13, 13)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 637, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarFactura();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDevolucionActionPerformed
        registrarDevolucion();
    }//GEN-LAST:event_botonDevolucionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDevolucion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbMueble;
    private javax.swing.JLabel lbNIT;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lblMueble;
    private javax.swing.JLabel lblNIT;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField textoNumeroFactura;
    // End of variables declaration//GEN-END:variables
}
