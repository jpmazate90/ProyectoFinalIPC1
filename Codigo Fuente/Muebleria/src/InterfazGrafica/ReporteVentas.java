
package InterfazGrafica;

import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import Logica.HTML;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReporteVentas extends javax.swing.JDialog {
    //atributos de la clase
    private DefaultTableModel model;
    private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
    private final String FECHA_MINIMA = "01/01/1950";
    private final String FECHA_MAXIMA = "31/12/2800";
    private CargarArchivo cargador = new CargarArchivo();
    private GuardarArchivo guardador = new GuardarArchivo();
    private String usuario;
// constructor
    public ReporteVentas(java.awt.Frame parent, String usuario) {
        super(parent, true);
        initComponents();
        this.usuario=usuario;
        model= new DefaultTableModel();
        model.addColumn("Mueble");
        model.addColumn("Identificador Unico");
        model.addColumn("Total");
        model.addColumn("Usuario Que Lo Vendio");
        model.addColumn("Cliente");
        model.addColumn("NIT");
        model.addColumn("Fecha");
        this.tablaVentas.setModel(model);
        ocultarDatos();

    }
    // asigna valores
    public void asignarValoresTabla(){
        Date minimo;
        Date maximo;
        
        try {
            refrescarTabla();
            if(textoMinimo.getText().equals("  /  /    ") && textoMaximo.getText().equals("  /  /    ")){
                minimo = formatoSinHora.parse(FECHA_MINIMA);
                maximo = formatoSinHora.parse(FECHA_MAXIMA);
            }else{
                minimo = formatoSinHora.parse(textoMinimo.getText());
                maximo = formatoSinHora.parse(textoMaximo.getText());
            }
            // muestra la carga de ventas
            mostrarDatos();
            cargador.cargarVentasIntervalo(model, minimo, maximo);
            asignarDineroIngresado();
  
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Alguna fecha esta incorrecta");
        }
    }
    // muestra el dinero que ingreso
    public void asignarDineroIngresado(){
        double dinero=0;
        for(int i=0;i<model.getRowCount();i++){
            dinero += Double.parseDouble((String)model.getValueAt(i, 2));
        }
        textDinero.setText(Double.toString(dinero));
    }
    // refresca la tabla
    public void refrescarTabla(){
        for(int i=0;i<model.getRowCount();i++){
            model.removeRow(i);
        }
    }
    // oculta datos
    public void ocultarDatos(){
        tablaVentas.setVisible(false);
        botonHTML.setVisible(false);
        textDinero.setVisible(false);
        lblDineroI.setVisible(false);
        textDinero.setText("");
    }
    // muestra datos
    public void mostrarDatos(){
        tablaVentas.setVisible(true);
        botonHTML.setVisible(true);
        textDinero.setVisible(true);
        lblDineroI.setVisible(true);
    }
    // exporta a html
    public void exportarHTML(){
        File archivo = HTML.usarFileChooser();
        if(archivo.getName().equals("null.html")==false){
            HTML.generarTitulo(archivo, "REPORTE VENTAS");
            HTML.generarReporteVentas(archivo, model, textoMinimo.getText(), textoMaximo.getText(), textDinero.getText(), this.usuario);
        }else{
            JOptionPane.showMessageDialog(null, "No se creo el archivo");
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textoMinimo = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoMaximo = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        botonHTML = new javax.swing.JButton();
        lblDineroI = new javax.swing.JLabel();
        textDinero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        try {
            textoMinimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoMinimoKeyTyped(evt);
            }
        });

        jLabel1.setText("REPORTE VENTAS");

        jLabel2.setText("De que fecha: ");

        jLabel3.setText("A que fecha:");

        try {
            textoMaximo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        textoMaximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoMaximoActionPerformed(evt);
            }
        });
        textoMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoMaximoKeyTyped(evt);
            }
        });

        jButton1.setText("Cargar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaVentas);

        botonHTML.setText("Exportar HTML");
        botonHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHTMLActionPerformed(evt);
            }
        });

        lblDineroI.setText("DINERO INGRESADO: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(textoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(389, 389, 389)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(botonHTML)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(253, 253, 253)
                .addComponent(lblDineroI)
                .addGap(18, 18, 18)
                .addComponent(textDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(558, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDineroI)
                    .addComponent(textDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(botonHTML)
                .addContainerGap())
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
        refrescarTabla();
        asignarValoresTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textoMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoMaximoKeyTyped
        refrescarTabla();
        ocultarDatos();
    }//GEN-LAST:event_textoMaximoKeyTyped

    private void textoMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoMinimoKeyTyped
        refrescarTabla();
        ocultarDatos();
    }//GEN-LAST:event_textoMinimoKeyTyped

    private void textoMaximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoMaximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoMaximoActionPerformed

    private void botonHTMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonHTMLActionPerformed
       exportarHTML();
    }//GEN-LAST:event_botonHTMLActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonHTML;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDineroI;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JLabel textDinero;
    private javax.swing.JFormattedTextField textoMaximo;
    private javax.swing.JFormattedTextField textoMinimo;
    // End of variables declaration//GEN-END:variables
}
