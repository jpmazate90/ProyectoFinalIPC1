
package InterfazGrafica;

import CargaDeDatos.ThreadCarga;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CargaDatos1 extends javax.swing.JInternalFrame {
    // variables de la interfaz grafica
    private Thread cargarDatos;
    private String archivoALeer;
    private String nombreArchivoALeer;
    private FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de imagen", "bin");
    private String direccionImagen;
    private int tiempoAleerCadaArchivo;
    private static DefaultListModel modeloLista;
    
    
    public CargaDatos1() {
        // inicia todo lo de la interfaz grafica y asigna el modelo a la lista
        initComponents();
        modeloLista = new DefaultListModel();
        jList2.setModel(modeloLista);
        //cerrar();
    }
    public DefaultListModel getModeloLista(){
        return this.modeloLista;
    }
    
    // metodo estatico que introduce lineas de texto a la lista 
    public void introducirDatosALaLista(String linea){
        modeloLista.addElement(linea);
    }
      
    // guarda informacion de la lista al final del programa, a la hora de cerrarlo
    public void guardarInformacionLista(){
        String[] componentesLista= new String[modeloLista.getSize()];
        int tamañoLista=modeloLista.getSize();
        for(int i=0;i<modeloLista.getSize();i++){
            componentesLista[i]=(String) modeloLista.getElementAt(i);
        }
        // abre un archivo
        File archivoAGuardar= new File("controlReportes.jp");
        for(int j=0;j<componentesLista.length;j++){
            // guarda datos de la lista
            try(FileWriter out = new FileWriter(archivoAGuardar,true); BufferedWriter archivoSalida = new BufferedWriter(out);){
                archivoSalida.write(componentesLista[j]);
                archivoSalida.newLine();
                archivoSalida.flush();
            }catch (IOException ex) {
                Logger.getLogger(CargaDatos1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
 
    public void cerrar(){
        try{// sirve para hacer algo al momento de que se cierre la ventana
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            //puede que no funcione
            addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    // llama a l metodo confirmar salida que guarda
                    confirmarSalida();
                }
            });
            this.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void confirmarSalida(){
        // a la hora de salir guarda la informacion de la lista y cierra
        System.out.println("Se esta guardando la informacion de la lista");
        guardarInformacionLista();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("CARGA DATOS");
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jScrollPane2.setViewportView(jList2);

        jLabel1.setText("CONTROL DATOS DE ENTRADA DEL TEXTO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
        );

        jMenu1.setText("Cargar Archivo");

        jMenuItem1.setText("Cargar un Archivo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //crea un file chooser para escoger el archivo a evaluar
        JFileChooser buscadorArchivos = new JFileChooser();
        String tiempoMilisegundos;
        int tiempoMilisegundosArchivo;
        int opcion = buscadorArchivos.showOpenDialog(this);
        // si se acepta el archivo entra
        if(opcion == JFileChooser.APPROVE_OPTION){
            String archivo = buscadorArchivos.getSelectedFile().getAbsolutePath();
            String archivo1 = buscadorArchivos.getSelectedFile().toString();
            // si se encuentra el archivo pide el tiempo en milisegundos
            System.out.println("Se ha encontrado el archivo: "+archivo1);
            tiempoMilisegundos = JOptionPane.showInputDialog("Introduce el tiempo que quieres que tarde en leer cada linea del archivo (Milisegundos): ");
            try {
                tiempoMilisegundosArchivo = Integer.parseInt(tiempoMilisegundos);
                System.out.println("tiempo: "+tiempoMilisegundos);
                this.archivoALeer = archivo;
                this.nombreArchivoALeer = archivo1;
                this.tiempoAleerCadaArchivo = tiempoMilisegundosArchivo;
                
            } catch (Exception e) {
                // si no se introdujo un dato entero muestra error y no hace nada mas
                JOptionPane.showMessageDialog(null, "No metiste un dato entero");
                System.out.println("No se introdujo un dato entero");
                
            }
            
            
                    
        }
        
        else if(opcion == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "No se ha cargado ningun archivo");
            System.out.println("No se ha cargado ningun archivo");
        }
        try{
            // crea el archivo que se selecciono
        File archivoAProcesar = new File(archivoALeer);
        
        // solo es prueba para ver si se crea exitosamente el archivo
        if(archivoAProcesar.exists()){
           // llama al metodo cargar datos de la clase thread
            System.out.println("Se ha almacenado con exito");
            cargarDatos = new ThreadCarga(archivoAProcesar, nombreArchivoALeer, tiempoAleerCadaArchivo, this);
            //inicia el hilo de los archivos
            cargarDatos.start();
        }
        }catch(Exception e){
            System.out.println("No se selecciono ningun archivo");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        confirmarSalida();
    }//GEN-LAST:event_formInternalFrameClosing

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
