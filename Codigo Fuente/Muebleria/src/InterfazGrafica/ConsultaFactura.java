package InterfazGrafica;

import Datos.Factura;
import Logica.CargarArchivo;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ConsultaFactura extends javax.swing.JDialog {
    
    // atributos de la clase
    private SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private CargarArchivo cargador = new CargarArchivo();
    // constructor
    public ConsultaFactura(java.awt.Frame parent) {
        super(parent,true);
        initComponents();
        ocultarDatos();
    }
    
    // busca la factura
    public void buscarFactura(){
        try{
        Factura factura;
        // muestra si se cargo o no
        factura = cargador.cargarFactura(Integer.parseInt(textoNumeroFactura.getText()));
        if(factura==null){
            JOptionPane.showMessageDialog(null,"No existe el registro de esa factura");
        }else{
            asignarValoresFactura(factura);
            mostrarDatos();
        }
        //excepcion
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"El numero de factura no es valido");
        }
    }
    // asigna los valores a la tabla
    public void asignarValoresFactura(Factura factura){
        
        textoCliente.setText(factura.getNombreCliente());
        textoDireccion.setText(factura.getDireccion());
        textoFactura.setText(Integer.toString(factura.getNumeroFactura()));
        textoFecha.setText(formato.format(factura.getFechaCompra()));
        textoIdentificador.setText(factura.getIdentificador());
        textoMueble.setText(factura.getNombreMueble());
        textoNIT.setText(Integer.toString(factura.getNIT()));
        textoNumeroFactura.setText(Integer.toString(factura.getNumeroFactura()));
        textoTotal.setText(Double.toString(factura.getTotal()));
        textoUsuario.setText(factura.getUsuarioVenta());
    }
    //oculta los datos
    public void ocultarDatos(){
        lblCliente.setVisible(false);
        lblDireccion.setVisible(false);
        lblFactura.setVisible(false);
        lblFecha.setVisible(false);
        lblIdentificador.setVisible(false);
        lblMueble.setVisible(false);
        lblNIT.setVisible(false);
        lblTotal.setVisible(false);
        lblUsuario.setVisible(false);
        textoCliente.setVisible(false);
        textoDireccion.setVisible(false);
        textoFactura.setVisible(false);
        textoFecha.setVisible(false);
        textoIdentificador.setVisible(false);
        textoMueble.setVisible(false);
        textoNIT.setVisible(false);
        textoTotal.setVisible(false);
        textoUsuario.setVisible(false);
        
    }
    // muestra los datos
    public void mostrarDatos(){
        lblCliente.setVisible(true);
        lblDireccion.setVisible(true);
        lblFactura.setVisible(true);
        lblFecha.setVisible(true);
        lblIdentificador.setVisible(true);
        lblMueble.setVisible(true);
        lblNIT.setVisible(true);
        lblTotal.setVisible(true);
        lblUsuario.setVisible(true);
        textoCliente.setVisible(true);
        textoDireccion.setVisible(true);
        textoFactura.setVisible(true);
        textoFecha.setVisible(true);
        textoIdentificador.setVisible(true);
        textoMueble.setVisible(true);
        textoNIT.setVisible(true);
        textoTotal.setVisible(true);
        textoUsuario.setVisible(true);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoNumeroFactura = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblNIT = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblFactura = new javax.swing.JLabel();
        lblIdentificador = new javax.swing.JLabel();
        lblMueble = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        textoNIT = new javax.swing.JLabel();
        textoDireccion = new javax.swing.JLabel();
        textoCliente = new javax.swing.JLabel();
        textoFecha = new javax.swing.JLabel();
        textoFactura = new javax.swing.JLabel();
        textoIdentificador = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JLabel();
        textoMueble = new javax.swing.JLabel();
        textoTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CONSULTA DE FACTURAS");

        jLabel2.setText("Ingresa el Numero de factura a consultar:");

        textoNumeroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNumeroFacturaKeyTyped(evt);
            }
        });

        jButton1.setText("Buscar Factura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblNIT.setText("NIT:");

        lblCliente.setText("Nombre Cliente:");

        lblDireccion.setText("Direccion:");

        lblFecha.setText("Fecha Compra:");

        lblFactura.setText("Numero Factura:");

        lblIdentificador.setText("Identificador Mueble:");

        lblMueble.setText("Mueble:");

        lblTotal.setText("Total:");

        lblUsuario.setText("Usuario Que Registro Compra:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textoNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(lblIdentificador)))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1)
                                    .addComponent(textoIdentificador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoMueble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNIT)
                            .addComponent(lblDireccion)
                            .addComponent(lblFecha)
                            .addComponent(lblFactura)
                            .addComponent(lblCliente))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoNIT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal)
                            .addComponent(lblMueble)
                            .addComponent(lblUsuario))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNIT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoNIT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDireccion)
                        .addComponent(lblUsuario)
                        .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCliente)
                            .addComponent(textoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdentificador)
                            .addComponent(textoIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(lblMueble)
                    .addComponent(textoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoMueble, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFactura)
                    .addComponent(lblTotal)
                    .addComponent(textoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarFactura();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textoNumeroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNumeroFacturaKeyTyped
        ocultarDatos();
    }//GEN-LAST:event_textoNumeroFacturaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdentificador;
    private javax.swing.JLabel lblMueble;
    private javax.swing.JLabel lblNIT;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel textoCliente;
    private javax.swing.JLabel textoDireccion;
    private javax.swing.JLabel textoFactura;
    private javax.swing.JLabel textoFecha;
    private javax.swing.JLabel textoIdentificador;
    private javax.swing.JLabel textoMueble;
    private javax.swing.JLabel textoNIT;
    private javax.swing.JTextField textoNumeroFactura;
    private javax.swing.JLabel textoTotal;
    private javax.swing.JLabel textoUsuario;
    // End of variables declaration//GEN-END:variables
}
