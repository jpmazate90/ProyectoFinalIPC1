
package InterfazGrafica;

import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import Logica.HTML;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ReporteGanancias extends javax.swing.JDialog {
    //atributos de la clase
    private CargarArchivo cargador = new CargarArchivo();
    private GuardarArchivo guardador = new GuardarArchivo();
    private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
    private final String FECHA_MINIMA = "01/01/1950";
    private final String FECHA_MAXIMA = "31/12/2800";
    private String usuario;
    // constructor
    public ReporteGanancias(java.awt.Frame parent, String usuario) {
        super(parent, true);
        initComponents();
        this.usuario=usuario;
        ocultarDatos();
    }
    // genera las ganancias
    public void generarGanancias(){
        Date minimo;
        Date maximo;
        try {
            if(textoMinimo.getText().equals("  /  /    ") && textoMaximo.getText().equals("  /  /    ")){
                minimo = formatoSinHora.parse(FECHA_MINIMA);
                maximo = formatoSinHora.parse(FECHA_MAXIMA);
            }else{
                minimo = formatoSinHora.parse(textoMinimo.getText());
                maximo = formatoSinHora.parse(textoMaximo.getText());
            }
            
            mostrarDatos();
            double ganancias=0;// carga las facturas
            ganancias = cargador.cargarFacturaGanancias(minimo, maximo);
            lblGananciasNumero.setText(Double.toString(ganancias));
            mostrarDatos();
            // si no esta bien una fecha agarra la excepcion
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Alguna fecha esta incorrecta");
        }
    }
    // oculta los datos
    public void ocultarDatos(){
        lblGanancias.setVisible(false);
        lblGananciasNumero.setVisible(false);
        botonHTML.setVisible(false);
        lblGananciasNumero.setText("");
    }
    // muestra los datos
    public void mostrarDatos(){
        lblGanancias.setVisible(true);
        lblGananciasNumero.setVisible(true);
        botonHTML.setVisible(true);
    }
    // exporta a html
    public void exportarHTML(){
        File archivo = HTML.usarFileChooser();
        if(archivo.getName().equals("null.html")==false){
            HTML.generarTitulo(archivo, "REPORTE GANANCIAS");
            HTML.generarReporteGanancias(archivo, textoMinimo.getText(), textoMaximo.getText(), lblGananciasNumero.getText(), usuario);
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
        lblGanancias = new javax.swing.JLabel();
        lblGananciasNumero = new javax.swing.JLabel();
        botonHTML = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("REPORTE GANANCIAS");

        jLabel2.setText("De que fecha: ");

        jLabel3.setText("A que fecha:");

        try {
            textoMinimo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            textoMaximo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblGanancias.setText("Ganancias en el tiempo especificado:");

        botonHTML.setText("Exportar HTML");
        botonHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHTMLActionPerformed(evt);
            }
        });

        jButton2.setText("Cargar Reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(textoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botonHTML))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jButton2))))
                            .addComponent(lblGanancias)
                            .addComponent(lblGananciasNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(36, 36, 36)
                .addComponent(lblGanancias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGananciasNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(botonHTML)
                .addContainerGap(89, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        generarGanancias();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void botonHTMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonHTMLActionPerformed
        exportarHTML();
    }//GEN-LAST:event_botonHTMLActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonHTML;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblGanancias;
    private javax.swing.JLabel lblGananciasNumero;
    private javax.swing.JFormattedTextField textoMaximo;
    private javax.swing.JFormattedTextField textoMinimo;
    // End of variables declaration//GEN-END:variables
}
