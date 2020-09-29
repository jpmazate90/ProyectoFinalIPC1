
package InterfazGrafica;

import Logica.CargarArchivo;
import Logica.Ordenamiento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jpmazate
 */
public class VerMueblesCreados extends javax.swing.JDialog {
// atributos de la clase
    private DefaultTableModel model;
    private DefaultTableModel modeloOrdenado;
    private DefaultTableModel modeloOrdenado2;
    private CargarArchivo cargador = new CargarArchivo();
    private SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat formatoSinHora= new SimpleDateFormat("dd/MM/yyy");
    // constructor
    public VerMueblesCreados(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        model= new DefaultTableModel();
        modeloOrdenado= new DefaultTableModel();
        modeloOrdenado2= new DefaultTableModel();
        // asigna las columnas a la tabla
        model.addColumn("Fecha Ensamble");
        model.addColumn("Identificador Unico");
        model.addColumn("Mueble");
        model.addColumn("Usuario Que lo Ensamblo");
        model.addColumn("Precio Costo");
        model.addColumn("Esta a la Venta");
        modeloOrdenado.addColumn("Fecha Ensamble");
        modeloOrdenado.addColumn("Identificador Unico");
        modeloOrdenado.addColumn("Mueble");
        modeloOrdenado.addColumn("Usuario Que lo Ensamblo");
        modeloOrdenado.addColumn("Precio Costo");
        modeloOrdenado.addColumn("Esta a la Venta");
        modeloOrdenado2.addColumn("Fecha Ensamble");
        modeloOrdenado2.addColumn("Identificador Unico");
        modeloOrdenado2.addColumn("Mueble");
        modeloOrdenado2.addColumn("Usuario Que lo Ensamblo");
        modeloOrdenado2.addColumn("Precio Costo");
        modeloOrdenado2.addColumn("Esta a la Venta");
        this.tablaMuebles.setModel(model);
        asignarMueblesTabla();
        
    }
// asigna los muebles a la tabla
    public void asignarMueblesTabla(){
        cargador.cargarTodosMueblesEnsamblados(model);
    }
    // ordena los datos por la fecha
    public void ordenar(boolean queForma){
        ArrayList<Date> fechas = new ArrayList<Date>();
        
        for(int i=0; i<tablaMuebles.getRowCount();i++){
            String fecha=null;
            try {
                
                fecha = (String) tablaMuebles.getValueAt(i, 0);
                fechas.add(formato.parse(fecha));
            } catch (ParseException e) {
                try {
                    fechas.add(formatoSinHora.parse(fecha));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "No es una fecha");
                }
            }            
        }// hace ordenamiento por burbuja
        fechas = Ordenamiento.ordenamientoPorBurbuja(fechas);
        for (int i = 0; i < fechas.size(); i++) {
            System.out.println(fechas.get(i));
            
        }
        
        Date cantidadPieza;
        boolean[] estadoPieza = new boolean[fechas.size()];
        for(int i=0; i<estadoPieza.length;i++){
            estadoPieza[i]=true;
        }
        // si es forma true es ascendente
        if(queForma==true){   
            // logica para ordenar
            for(int i=tablaMuebles.getRowCount()-1;i>=0;i--){
                for(int j=0;j<tablaMuebles.getRowCount();j++){
                    cantidadPieza =Ordenamiento.formatearFecha((String)tablaMuebles.getValueAt(j, 0));
                    if(fechas.get(i).compareTo(cantidadPieza)==0 && estadoPieza[i]==true){
                        String[] datos = new String[6];
                        if(verificarSiYaExisteMueble((String)tablaMuebles.getValueAt(j, 1), modeloOrdenado)==true){
                            for(int k=0;k<datos.length;k++){
                                datos[k] = (String)tablaMuebles.getValueAt(j, k);
                            }
                            modeloOrdenado.addRow(datos);
                            estadoPieza[i]=false;
                        }
                        
                    }
                }
            }
            modeloOrdenado.setRowCount(model.getRowCount());
            this.tablaMuebles.setModel(modeloOrdenado);
            
            // si es false es descendente
        }else{
            // logica para ordenar
            for(int i=0; i<tablaMuebles.getRowCount();i++){
                for(int j=0;j<tablaMuebles.getRowCount();j++){
                   cantidadPieza =Ordenamiento.formatearFecha((String)tablaMuebles.getValueAt(j, 0));
                   if(fechas.get(i).compareTo(cantidadPieza)==0 && estadoPieza[i]==true ){
                       String[] datos = new String[6];
                       if(verificarSiYaExisteMueble((String)tablaMuebles.getValueAt(j, 1), modeloOrdenado2)==true){
                            for(int k=0;k<datos.length;k++){
                                datos[k] = (String)tablaMuebles.getValueAt(j, k);
                            }
                            modeloOrdenado2.addRow(datos);
                            estadoPieza[i]=false;
                       }
                   }
                }
                
                
            }
            modeloOrdenado2.setRowCount(model.getRowCount());
            this.tablaMuebles.setModel(modeloOrdenado2);
        }

    }
    // verifica si ya existe un mueble en la lista
    public boolean verificarSiYaExisteMueble(String nombre, DefaultTableModel tabla){
        boolean sePuede=true;
        for(int i=0;i<tabla.getRowCount();i++){
            if(nombre.equals((String)tabla.getValueAt(i, 1))){
                return false;
            }
        }
        return sePuede;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMuebles = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaMuebles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaMuebles);

        jLabel1.setText("MUEBLES CREADOS");

        jButton1.setText("Ordenar Ascendente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ordenar Descendente");
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
                .addGap(294, 294, 294)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(375, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(406, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(116, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ordenar(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ordenar(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaMuebles;
    // End of variables declaration//GEN-END:variables
}
