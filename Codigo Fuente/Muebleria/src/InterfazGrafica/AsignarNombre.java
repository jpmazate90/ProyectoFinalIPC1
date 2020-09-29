
package InterfazGrafica;

import Datos.Mueble;
import Logica.GuardarArchivo;
import Logica.Validaciones;
import javax.swing.JOptionPane;

public class AsignarNombre extends javax.swing.JDialog {
    //atributos de la clase
    private Validaciones validador = new Validaciones();
    private GuardarArchivo guardador = new GuardarArchivo();
// constructor
    public AsignarNombre(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        ocultarDatos();
    }
    // valida el nomrbe, mira si ya existe
    public void validarNombre(){
        boolean existeMueble = validador.verificarMueble(textoNombre.getText());
        if(existeMueble==true){
            JOptionPane.showMessageDialog(null, "El mueble que quieres crear ya existe");
        }else{
            JOptionPane.showMessageDialog(null, "Muy bien asignale el precio");
            mostrarDatos();
            
        }
    }
    // verifica si esta bien el precio
    public void verificarPrecio(){
        Mueble muebleAGuardar;
        try{
            double precio = Double.parseDouble(textoPrecio.getText());
            muebleAGuardar = new Mueble(textoNombre.getText(), precio);
            guardador.guardarMueble(muebleAGuardar);
            JOptionPane.showMessageDialog(null, "Se creo correctamente el mueble "
                                                + "para agregarle las piezas ve al menu Agregar Piezas");
            this.setVisible(false);
            // excepciones
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "No es un numero lo que introdujiste");
            textoPrecio.setText("");
            ocultarDatos();
        }
    }
    // oculta los datos 
    public void ocultarDatos(){
        textoPrecio.setVisible(false);
        lblPrecio.setVisible(false);
        botonCrear.setVisible(false);
    }
    // muestra los datos
    public void mostrarDatos(){
        textoPrecio.setVisible(true);
        lblPrecio.setVisible(true);
        botonCrear.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        textoPrecio = new javax.swing.JTextField();
        botonCrear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ASIGNAR NOMBRE");

        jLabel2.setText("NOMBRE MUEBLE NUEVO:");

        jLabel3.setText("NOTA: No se puede repetir el nombre.");

        textoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoNombreKeyTyped(evt);
            }
        });

        jButton1.setText("Verificar Nombre");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblPrecio.setText("Precio:");

        botonCrear.setText("Crear Mueble.");
        botonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblPrecio)
                                .addGap(30, 30, 30)
                                .addComponent(textoPrecio))
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addComponent(botonCrear)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(textoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCrear))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        validarNombre();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textoNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoNombreKeyTyped
        ocultarDatos();
    }//GEN-LAST:event_textoNombreKeyTyped

    private void botonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearActionPerformed
        verificarPrecio();
    }//GEN-LAST:event_botonCrearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoPrecio;
    // End of variables declaration//GEN-END:variables
}
