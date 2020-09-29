
package InterfazGrafica;

import Datos.ControlNumeroPiezas;
import Datos.MueblePiezas1;
import Datos.Pieza;
import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import Logica.Validaciones;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AgregarPiezasMueble extends javax.swing.JDialog {

    //atributos de la clase
    private DefaultListModel modeloLista;
    private CargarArchivo cargador = new CargarArchivo();
    private Validaciones validador = new Validaciones();
    private GuardarArchivo guardador = new GuardarArchivo();
    //constructor
    public AgregarPiezasMueble(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        modeloLista = new DefaultListModel();
        listaMuebles.setModel(modeloLista);
        introducirDatosLista();

    }
    //introduce en la lista
    public void introducirDatosLista(){
        cargador.cargarMuebleslista(modeloLista);
    }
    //valida la pieza
    public void validarPieza(){
        if(verCasillasEnBlanco()==false){
            MueblePiezas1 mueblePiezas;
            // mira si existen piezas
            boolean existePieza = cargador.existePieza(textoPieza.getText());
            if(existePieza == false){
                verificarPiezas();
            }// valida las piezas
                if(validarCantidad()==true){
                    mueblePiezas = new MueblePiezas1(textoMueble.getText(), textoPieza.getText(), Integer.parseInt(textoCantidad.getText()));
                    guardador.guardarMueblePiezas(mueblePiezas);
                    JOptionPane.showMessageDialog(null,"Se le asignaron las piezas al mueble");
                    this.setVisible(false);
                }
            
        }
    }
    
    public boolean validarCantidad(){
        try{// mira si se puede validar la cantidad
            int cantidad = Integer.parseInt(textoCantidad.getText());
            System.out.println("");
            return true;
            //excepciones
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "No es un numero entero lo que introduciste");
            textoCantidad.setText("");
            return false;
        }
    }
    // verifica las piezas
    public void verificarPiezas(){
        String pieza = textoPieza.getText();
        ControlNumeroPiezas piezas;
        Pieza piezaAGuardar;
        try{// crea la pieza y la guarda
            double precio = 0;
            piezas = cargador.cargarContadorPiezas();
            piezaAGuardar= new Pieza(pieza, precio, piezas.getPieza());
            piezaAGuardar.setEstadoPieza(false);
            guardador.guardarPieza(piezaAGuardar, Integer.toString(piezas.getPieza()));
            piezas.setPieza(piezas.getPieza()+1);
            guardador.guardarContadorPieza(piezas);
            //muestra que se creo
            JOptionPane.showMessageDialog(null, "Se registro la pieza: "+pieza);    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // verifica si hay alguna casilla en blanco
    public boolean verCasillasEnBlanco(){
        if(textoPieza.getText()==null || textoPieza.getText().equals("") || textoCantidad.getText()==null || textoCantidad.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Por favor llena las casillas en blanco");
            return true;
        }else{
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaMuebles = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoMueble = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textoPieza = new javax.swing.JTextField();
        textoCantidad = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        listaMuebles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMueblesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaMuebles);

        jLabel1.setText("Asignar Piezas Mueble");

        jLabel2.setText("Mueble:");

        jLabel3.setText("Pieza:");

        jLabel4.setText("Cantidad:");

        jButton1.setText("Asignar Piezas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Da click al mueble que le quieres agregar piezas.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textoMueble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoPieza)
                        .addComponent(textoCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                    .addComponent(jButton1))
                .addGap(23, 23, 23))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(textoMueble, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(textoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
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

    private void listaMueblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMueblesMouseClicked
        //pone la informacion en los textos
        int seleccion = listaMuebles.getSelectedIndex();
        textoMueble.setText(String.valueOf(modeloLista.getElementAt(seleccion)));
    }//GEN-LAST:event_listaMueblesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        validarPieza();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaMuebles;
    private javax.swing.JTextField textoCantidad;
    private javax.swing.JLabel textoMueble;
    private javax.swing.JTextField textoPieza;
    // End of variables declaration//GEN-END:variables
}
