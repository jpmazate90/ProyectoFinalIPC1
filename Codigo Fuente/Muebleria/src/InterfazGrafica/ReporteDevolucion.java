
package InterfazGrafica;

import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import Logica.HTML;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReporteDevolucion extends javax.swing.JDialog {
// atributos de la clase
    private DefaultTableModel model;
    private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
    private final String FECHA_MINIMA = "01/01/1950";
    private final String FECHA_MAXIMA = "31/12/2800";
    private CargarArchivo cargador = new CargarArchivo();
    private GuardarArchivo guardador = new GuardarArchivo();
    private String usuario;
    // constructor
    public ReporteDevolucion(java.awt.Frame parent, String usuario) {
        super(parent, true);
        initComponents();
        this.usuario = usuario;
        model = new DefaultTableModel();
        // asigna las columnas a la tabla
        model.addColumn("Fecha Compra");
        model.addColumn("Fecha Devolucion");
        model.addColumn("Identificador Mueble");
        model.addColumn("Mueble");
        model.addColumn("Costo");
        model.addColumn("Numero Factura");
        this.tablaDevoluciones.setModel(model);
        ocultarDatos();

    }
    // asigna valores a la tabla
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
            // manda a cargar las devoluciones
            mostrarDatos();
            cargador.cargarDevolucionesIntervalo(model, minimo, maximo);
            asignarDineroDevuelto();
            
            
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Alguna fecha esta incorrecta");
        }
    }
    // refresca la tabla
    public void refrescarTabla(){
        for(int i=0;i<model.getRowCount();i++){
            model.removeRow(i);
        }
    }
    //muestra el dinero devuelto
    public void asignarDineroDevuelto(){
        double dinero=0;
        for(int i=0;i<model.getRowCount();i++){
            dinero += Double.parseDouble((String)model.getValueAt(i, 4));
        }
        textoDinero.setText(Double.toString(dinero));
    }
    
    
    // oculta datos
    public void ocultarDatos(){
        tablaDevoluciones.setVisible(false);
        botonHTML.setVisible(false);
        textoDinero.setVisible(false);
        textoDinero.setText("");
        lblDinero.setVisible(false);
    }// muestra datos
    public void mostrarDatos(){
        tablaDevoluciones.setVisible(true);
        botonHTML.setVisible(true);
        lblDinero.setVisible(true);
        textoDinero.setVisible(true);
    }
    // exporta a html
    public void exportarHTML(){
        File archivo = HTML.usarFileChooser();
        if(archivo.getName().equals("null.html")==false){
            HTML.generarTitulo(archivo, "REPORTE DEVOLUCIONES");
            HTML.generarReporteDevoluciones(archivo, model, textoMinimo.getText(), textoMaximo.getText(), textoDinero.getText(), this.usuario);
        }else{
            JOptionPane.showMessageDialog(null, "No se creo el archivo");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoMinimo = new javax.swing.JFormattedTextField();
        textoMaximo = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDevoluciones = new javax.swing.JTable();
        botonHTML = new javax.swing.JButton();
        lblDinero = new javax.swing.JLabel();
        textoDinero = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REPORTE DEVOLUCIONES");

        jLabel2.setText("De que fecha:");

        jLabel3.setText("A que fecha:");

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

        try {
            textoMaximo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
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

        tablaDevoluciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaDevoluciones);

        botonHTML.setText("Exportar HTML");
        botonHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHTMLActionPerformed(evt);
            }
        });

        lblDinero.setText("Dinero Devuelto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(textoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 705, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(81, 81, 81))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(botonHTML))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(lblDinero)
                        .addGap(35, 35, 35)
                        .addComponent(textoDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDinero)
                    .addComponent(textoDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(botonHTML)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refrescarTabla();
        asignarValoresTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textoMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoMinimoKeyTyped
        refrescarTabla();
        ocultarDatos();
        
    }//GEN-LAST:event_textoMinimoKeyTyped

    private void textoMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoMaximoKeyTyped
        refrescarTabla();
        ocultarDatos();
    }//GEN-LAST:event_textoMaximoKeyTyped

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
    private javax.swing.JLabel lblDinero;
    private javax.swing.JTable tablaDevoluciones;
    private javax.swing.JLabel textoDinero;
    private javax.swing.JFormattedTextField textoMaximo;
    private javax.swing.JFormattedTextField textoMinimo;
    // End of variables declaration//GEN-END:variables
}
