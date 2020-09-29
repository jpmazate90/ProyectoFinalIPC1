
package InterfazGrafica;

import Datos.ControlNumeroPiezas;
import Datos.Pieza;
import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jpmazate
 */
public class AgregarPiezas extends javax.swing.JDialog {
    //atributos de la clase
    private GuardarArchivo guardador = new GuardarArchivo();
    private CargarArchivo cargador = new CargarArchivo();
// constructor
    public AgregarPiezas(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        
    }
    // metodo que verifica las piezas
    public void verificarPiezas(){
        String pieza = nombrePieza.getText();
        ControlNumeroPiezas piezas;
        Pieza piezaAGuardar;
        try{
            //guarda la pieza que se creo
            double precio = Double.parseDouble(precioPieza.getText());
            piezas = cargador.cargarContadorPiezas();
            piezaAGuardar= new Pieza(pieza, precio, piezas.getPieza());
            guardador.guardarPieza(piezaAGuardar, Integer.toString(piezas.getPieza()));
         //muestra que se registro
            piezas.setPieza(piezas.getPieza()+1);
            guardador.guardarContadorPieza(piezas);
            JOptionPane.showMessageDialog(null, "Se registro la pieza: "+pieza);
            
            //excepciones
        }catch(Exception e){
            labelMensaje.setText("No es un numero lo que introduciste");
            labelMensaje.setVisible(true);
            e.printStackTrace();
            
        }finally{
            precioPieza.setText("");
            nombrePieza.setText("");
        }
    }
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mensajeError = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombrePieza = new javax.swing.JTextField();
        precioPieza = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        labelMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre Pieza:");

        jLabel2.setText("Precio:");

        jLabel3.setText("Registrar Pieza");

        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mensajeErrorLayout = new javax.swing.GroupLayout(mensajeError);
        mensajeError.setLayout(mensajeErrorLayout);
        mensajeErrorLayout.setHorizontalGroup(
            mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeErrorLayout.createSequentialGroup()
                .addGroup(mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mensajeErrorLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel3))
                    .addGroup(mensajeErrorLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mensajeErrorLayout.createSequentialGroup()
                                .addGroup(mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(nombrePieza, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(precioPieza)))))
                    .addGroup(mensajeErrorLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jButton1)))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        mensajeErrorLayout.setVerticalGroup(
            mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeErrorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addGroup(mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombrePieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(mensajeErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mensajeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mensajeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        verificarPiezas();
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelMensaje;
    private javax.swing.JPanel mensajeError;
    private javax.swing.JTextField nombrePieza;
    private javax.swing.JTextField precioPieza;
    // End of variables declaration//GEN-END:variables
}
