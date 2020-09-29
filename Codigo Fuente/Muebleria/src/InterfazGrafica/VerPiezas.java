
package InterfazGrafica;

import Logica.CargarArchivo;
import Logica.Ordenamiento;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VerPiezas extends javax.swing.JDialog {
    
    private CargarArchivo cargador = new CargarArchivo();
    private DefaultTableModel model;
    private DefaultTableModel modeloOrdenado;
    private DefaultTableModel modeloAuxiliar;
    private DefaultTableModel modeloOrdenado2;
// constructor
    public VerPiezas(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        model= new DefaultTableModel();
        modeloOrdenado= new DefaultTableModel();
        modeloOrdenado2 = new DefaultTableModel();
        model.addColumn("Nombre Pieza");
        model.addColumn("Cantidad");
        modeloOrdenado.addColumn("Nombre Pieza");
        modeloOrdenado.addColumn("Cantidad");
        modeloOrdenado2.addColumn("Nombre Pieza");
        modeloOrdenado2.addColumn("Cantidad");
        this.tablaPiezas1.setModel(model);
        asignarValoresTabla();
    }
    // asigna valores a la tabla
    public void asignarValoresTabla(){
        cargador.cargarNombrePiezas(model);
    }
    // ordena
    public void ordenar(boolean queForma){
        // ordena por medio de quicksort
        ArrayList<Integer> cantidad = new ArrayList<Integer>();
        int tabla=tablaPiezas1.getRowCount();
        
        for(int i=0; i<tablaPiezas1.getRowCount();i++){
            cantidad.add(Integer.parseInt((String)tablaPiezas1.getValueAt(i, 1)));
        }
        // luego de ordenar entra
        cantidad = Ordenamiento.ordenar(cantidad, 0, cantidad.size()-1);
        int cantidadPieza;
        boolean[] estadoPieza = new boolean[cantidad.size()];
        for(int i=0; i<estadoPieza.length;i++){
            estadoPieza[i]=true;
        }
        
            // si es true es ascendente
        if(queForma==true){   
            // logica para ordenar
            for(int i=tablaPiezas1.getRowCount()-1;i>=0;i--){
                for(int j=0;j<tablaPiezas1.getRowCount();j++){
                    cantidadPieza =Integer.parseInt((String)tablaPiezas1.getValueAt(j, 1));
                    if(cantidad.get(i)==cantidadPieza && estadoPieza[i]==true){
                        String[] datos = new String[2];
                        if(verificarSiYaExisteEseNombre((String)tablaPiezas1.getValueAt(j, 0), modeloOrdenado)==true){
                            datos[0] = (String)tablaPiezas1.getValueAt(j, 0);
                            datos[1] = (String)tablaPiezas1.getValueAt(j, 1);
                            modeloOrdenado.addRow(datos);
                            estadoPieza[i]=false;
                        }
                        
                    }
                }
            }
            modeloOrdenado.setRowCount(model.getRowCount());
            this.tablaPiezas1.setModel(modeloOrdenado);
            
            
        }else{
            // si es false es descendente
            for(int i=0; i<tablaPiezas1.getRowCount();i++){
                for(int j=0;j<tablaPiezas1.getRowCount();j++){
                    
                    // logica para ordenar
                   cantidadPieza =Integer.parseInt((String)tablaPiezas1.getValueAt(j, 1));
                   if(cantidad.get(i)==cantidadPieza && estadoPieza[i]==true ){
                       String[] datos = new String[2];
                       if(verificarSiYaExisteEseNombre((String)tablaPiezas1.getValueAt(j, 0), modeloOrdenado2)==true){
                            datos[0] = (String)tablaPiezas1.getValueAt(j, 0);
                            datos[1] = (String)tablaPiezas1.getValueAt(j, 1);
                            modeloOrdenado2.addRow(datos);
                            estadoPieza[i]=false;
                       }
                   }
                }
                
                
            }
            modeloOrdenado2.setRowCount(model.getRowCount());
            this.tablaPiezas1.setModel(modeloOrdenado2);
        }
        
        
        
    }
    // verifica si ya existe el nombre
    public boolean verificarSiYaExisteEseNombre(String nombre, DefaultTableModel tabla){
        boolean sePuede=true;
        for(int i=0;i<tabla.getRowCount();i++){
            if(nombre.equals((String)tabla.getValueAt(i, 0))){
                return false;
            }
        }
        return sePuede;
    }
    
// refresca el modelo
    public void refresarModelo(DefaultTableModel modelo){
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPiezas1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaPiezas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaPiezas1);

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

        jLabel1.setText("Informacion piezas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(jButton1)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(138, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(586, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)))
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(412, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tablaPiezas1;
    // End of variables declaration//GEN-END:variables
}
