
package InterfazGrafica;

import Logica.CargarArchivo;
import Logica.HTML;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReporteMuebleMenosVendido extends javax.swing.JDialog {
// atributos de la clase
    private CargarArchivo cargador = new CargarArchivo();
    private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
    private final String FECHA_MINIMA = "01/01/1950";
    private final String FECHA_MAXIMA = "31/12/2800";
    private DefaultTableModel model;
    private String usuario;
    // constructor
    public ReporteMuebleMenosVendido(java.awt.Frame parent, String usuario) {
        super(parent, true);
        initComponents();
        this.usuario=usuario;
        model = new DefaultTableModel();
        model.addColumn("Mueble");
        model.addColumn("Identificador Unico");
        model.addColumn("Total");
        model.addColumn("Usuario Venta");
        model.addColumn("Cliente");
        model.addColumn("NIT");
        model.addColumn("Fecha");
        this.tablaMueble.setModel(model);
        ocultarDatos();
    }
    
    // mueble menos vendido
    public void muebleMenosVendido(){
        Date minimo;
        Date maximo;
        try{
            refrescarTabla();
            if(textoMinimo.getText().equals("  /  /    ") && textoMaximo.getText().equals("  /  /    ")){
                minimo = formatoSinHora.parse(FECHA_MINIMA);
                maximo = formatoSinHora.parse(FECHA_MAXIMA);
            }else{
                minimo = formatoSinHora.parse(textoMinimo.getText());
                maximo = formatoSinHora.parse(textoMaximo.getText());
            }
            // carga el nombre del mueble menos vendido
            String mueble = cargador.cargarMuebleVendido(minimo, maximo, false);
            if(mueble!=null){
                lblMuebleMenosVendido.setText(mueble);
                mostrarDatos();
                cargador.cargarFacturasPorMueble(model, minimo, maximo, mueble);
                vecesVendido();  
            }// agarra la excepcion
        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Alguna fecha esta incorrecta");
        }
    }
    // muestra las veces que se vendio
    public void vecesVendido(){
        int veces = model.getRowCount();
        lblVecesVendido.setText(Integer.toString(veces));
    }
    // oculta datos
    public void ocultarDatos(){
        refrescarTabla();
        lblMueble.setVisible(false);
        lblMuebleMenosVendido.setVisible(false);
        lblMuebleMenosVendido.setText("");
        lblVeces.setVisible(false);
        lblVecesVendido.setVisible(false);
        lblVecesVendido.setText("");
        botonHTML.setVisible(false);
        tablaMueble.setVisible(false);
    }
    // muestra datos
    public void mostrarDatos(){
        lblMueble.setVisible(true);
        lblMuebleMenosVendido.setVisible(true);
        lblVeces.setVisible(true);
        lblVecesVendido.setVisible(true);
        botonHTML.setVisible(true);
        tablaMueble.setVisible(true);
    }
    // refresca tabla
    public void refrescarTabla(){
        for(int i=0;i<model.getRowCount();i++){
            model.removeRow(i);
        }
    }
    // exporta a html
    public void exportarHTML(){
        File archivo = HTML.usarFileChooser();
        if(archivo.getName().equals("null.html")==false){
            HTML.generarTitulo(archivo, "REPORTE MUEBLE MENOS VENDIDO");
            HTML.generarReporteMueble(archivo, model, textoMinimo.getText(), textoMaximo.getText(), lblMuebleMenosVendido.getText(), lblVecesVendido.getText(), this.usuario, false);
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
        lblMueble = new javax.swing.JLabel();
        lblMuebleMenosVendido = new javax.swing.JLabel();
        lblVeces = new javax.swing.JLabel();
        lblVecesVendido = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMueble = new javax.swing.JTable();
        botonHTML = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("MUEBLE MENOS VENDIDO");

        jLabel2.setText("DE QUE FECHA :");

        jLabel3.setText("A Que Fecha:");

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

        lblMueble.setText("Mueble Menos Vendido:");

        lblVeces.setText("Veces Vendido:");

        tablaMueble.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaMueble);

        botonHTML.setText("Exportar HTML");
        botonHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHTMLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoMinimo))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addGap(33, 33, 33))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblMueble)
                        .addGap(18, 18, 18)
                        .addComponent(lblMuebleMenosVendido, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(lblVeces)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVecesVendido, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonHTML)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblVeces)
                    .addComponent(lblVecesVendido, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMueble, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMuebleMenosVendido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonHTML)
                .addContainerGap(31, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoMinimoKeyTyped
        refrescarTabla();
        ocultarDatos();
    }//GEN-LAST:event_textoMinimoKeyTyped

    private void textoMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoMaximoKeyTyped
        refrescarTabla();
        ocultarDatos();
    }//GEN-LAST:event_textoMaximoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refrescarTabla();
        ocultarDatos();
        muebleMenosVendido();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JLabel lblMueble;
    private javax.swing.JLabel lblMuebleMenosVendido;
    private javax.swing.JLabel lblVeces;
    private javax.swing.JLabel lblVecesVendido;
    private javax.swing.JTable tablaMueble;
    private javax.swing.JFormattedTextField textoMaximo;
    private javax.swing.JFormattedTextField textoMinimo;
    // End of variables declaration//GEN-END:variables
}
