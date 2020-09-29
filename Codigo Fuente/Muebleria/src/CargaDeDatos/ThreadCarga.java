
package CargaDeDatos;


import Datos.ControlNumeroPiezas;
import Datos.EnsamblarMueble;
import Datos.Mueble;
import Datos.MueblePiezas;
import Datos.MueblePiezas1;
import Datos.Pieza;
import Datos.Usuario;
import InterfazGrafica.CargaDatos1;
import Logica.CargarArchivo;
import Logica.GuardarArchivo;
import Logica.Validaciones;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jpmazate
 */
public class ThreadCarga extends Thread {
    //variables privadas de la calse 
    private File archivo;
    private FileReader archivoDeEntrada;
    private String nombreArchivo;
    private int tiempoMilisegundos;
    private CargaDatos1 datos;
    private static final String USUARIO = "USUARIO";
    private static final String PIEZA = "PIEZA";
    private static final String MUEBLE = "MUEBLE";
    private static final String MUEBLE_PIEZAS= "MUEBLE_PIEZAS";
    private static final String ENSAMBLAR_MUEBLE = "ENSAMBLAR_MUEBLE";
    private static final String[] ORDENES =  { USUARIO, PIEZA, MUEBLE, MUEBLE_PIEZAS, ENSAMBLAR_MUEBLE};
    private static String lineaCodigo=null;
    private Validaciones validaciones;
    private GuardarArchivo guardador= new GuardarArchivo();
    private CargarArchivo cargador = new CargarArchivo();
    private ControlNumeroPiezas controladorPiezas ;
    
    
    //constructor(vacio)
    public ThreadCarga() {
    }
    // constructor que pide un archivo, un nombre y el tiempo 
    public ThreadCarga(File archivo, String nombre, int tiempoMilisegundos, CargaDatos1 datos){
        super(nombre);
        this.archivo = archivo;
        this.nombreArchivo = nombre;
        this.tiempoMilisegundos = tiempoMilisegundos;
        this.datos = datos;
        
        
    }
    // metodo que genera el hilo
    public void run(){
        try {// lee el archivo
            leerArchivo();
        } catch (FileNotFoundException ex) {
            datos.introducirDatosALaLista("No se puede conectar al archivo");
            System.out.println("No se puede leer el archivo");
        } catch (IOException ex) {
            Logger.getLogger(ThreadCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
    public void leerArchivo() throws FileNotFoundException, IOException{
        try{// abre la via para leer el archivo
            FileReader archivo1 = new FileReader(archivo);
            BufferedReader archivo= new BufferedReader(archivo1);
            // lee la primera linea y la guarda
            String auxiliar = archivo.readLine();
            this.lineaCodigo= auxiliar;
            //crear un try catch por si no tiene ningun "("
            int posicion;
            // variables para desarmar la linea ingresada
            String auxiliar3 ;
            String auxiliar4;
            String[] miembrosDeLinea;
        
            while(auxiliar !=null){
                try{
                    // guarda de donde a donde esta el nombre de la orden
                    
                posicion = auxiliar.indexOf("(");
                auxiliar3 = auxiliar.substring(0, posicion);
                
                auxiliar4 = auxiliar.substring(posicion);
                // quita las comillas 
                miembrosDeLinea=Validaciones.quitarComillasALaLinea(auxiliar4);

                // muestra en consola el resultado
                System.out.println(auxiliar);
                System.out.println(auxiliar3);
                System.out.println(auxiliar4);
                for (String string : miembrosDeLinea) {
                    System.out.println(string);
                }
                
                // llama segun la orden a cierta parte del metodo
                verificarObjetoALlamar(auxiliar3,miembrosDeLinea);
                    // lee la siguiente orden
                auxiliar= archivo.readLine();
                this.lineaCodigo=auxiliar;
            
                try {// lo que descansa el thread antes de continuar
                    Thread.sleep(tiempoMilisegundos);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadCarga.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }catch(ArrayIndexOutOfBoundsException e){
                    datos.introducirDatosALaLista("No cumple con el formato valido la linea: "+lineaCodigo);
                    auxiliar= archivo.readLine();
                }
            
            
            }// muestra que se completo la carga del archivo 
            JOptionPane.showMessageDialog(null, "Se ha completado la carga del archivo");
            datos.introducirDatosALaLista("Se ha completado la carga del archivo");
        }catch(Exception e){
            // si surge algun error dice que no tiene el formato valido 
            JOptionPane.showMessageDialog(null, "No tiene formato valido el archivo");
            datos.introducirDatosALaLista("No cumple con el formato valido la linea: "+lineaCodigo);
            System.out.println("error de formato no valido");
            e.printStackTrace();
            
            
        }
        
        
    }    
    
    public void verificarObjetoALlamar(String cadenaAVerificar, String[] datosLinea) throws IOException{
        // variables locales
        validaciones = new Validaciones(datos);
        // si la orden es un usuario
        if(cadenaAVerificar.equals(ORDENES[0])){
            Usuario usuarioAGuardar;
            String usuario= datosLinea[0];
            String password = datosLinea[1];
            int tipoArea= Integer.parseInt(datosLinea[2]);
            // verifica si puede registrarse el usuario
            boolean crearUsuario = validaciones.crearUsuario(usuario, password);
            
            if(crearUsuario==true){

                // guarda el usuario
                usuarioAGuardar = new Usuario(usuario, password, tipoArea);
                System.out.println("Se creo el usuario");
                System.out.println(usuarioAGuardar.getArea());
                guardador.guardarUsuario(usuarioAGuardar);
                datos.introducirDatosALaLista("Se creo el usuario: "+usuario);
            }else{
            }
        }
        // si es una pieza
        if(cadenaAVerificar.equals(ORDENES[1])){
            // asigna valores
            Pieza piezaAGuardar;
            String nombrePieza = datosLinea[0];
            double costo = Double.parseDouble(datosLinea[1]);
            controladorPiezas = cargador.cargarContadorPiezas();
            
            piezaAGuardar = new Pieza(nombrePieza, costo, controladorPiezas.getPieza());
            
            guardador.guardarPieza(piezaAGuardar, Integer.toString(controladorPiezas.getPieza()));
            controladorPiezas.setPieza(controladorPiezas.getPieza()+1);
            guardador.guardarContadorPieza(controladorPiezas);
            datos.introducirDatosALaLista("Se registro la pieza: "+nombrePieza+ " con un precio de: "+costo);

            
        }
        // si es un mueble
        if(cadenaAVerificar.equals(ORDENES[2])){
            Mueble muebleAGuardar;
            
            try {
                String nombreMueble= datosLinea[0];
                double precio = Double.parseDouble(datosLinea[1]);
                
                
                // verifica si se puede crear el mueble
                boolean existeMueble = validaciones.verificarMueble(nombreMueble);
                if(existeMueble==false){
                    // guarda  el mueble
                    muebleAGuardar = new Mueble(nombreMueble, precio);
                    guardador.guardarMueble(muebleAGuardar);
                    datos.introducirDatosALaLista("Se guardo el mueble: "+nombreMueble+" correctamente");
                }else{
                    datos.introducirDatosALaLista("Error Logico: el mueble "+nombreMueble+ " ya existe");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // si es creacion de mueble
        if(cadenaAVerificar.equals(ORDENES[3])){
            String nombreMueble;
            String nombrePieza;
            int numeroPiezas;
            MueblePiezas1 mueblePiezasAGuardar;
            try {// verifica si se puede crear
                nombreMueble=datosLinea[0];
                nombrePieza=datosLinea[1];
                numeroPiezas=Integer.parseInt(datosLinea[2]);
                boolean existeMueble = validaciones.verificarMueble(nombreMueble);
                boolean existePieza = validaciones.verificarExistePieza(nombrePieza);
                if(existeMueble==true && existePieza==true){
                    //crea las mueble piezas
                    mueblePiezasAGuardar = new MueblePiezas1(nombreMueble, nombrePieza, numeroPiezas);
                    guardador.guardarMueblePiezas(mueblePiezasAGuardar);
                    //guarda  y muestra en pantalla
                    datos.introducirDatosALaLista("Se ha asignado la pieza correctamente: "+nombrePieza+" al mueble: "+nombreMueble);
                }else{// si existen errores los muestra en pantalla
                    if(existeMueble==false && existePieza==true){
                        datos.introducirDatosALaLista("Error logico: no existe el mueble "+ nombreMueble);
                    }else{
                        datos.introducirDatosALaLista("Error logico: no existe la pieza:" +nombrePieza);
                    }
                }
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        if(cadenaAVerificar.equals(ORDENES[4])){
            EnsamblarMueble muebleEnsamblado;
            try{// es un arraylist porque nunca va a recibir muchas piezas
            ArrayList<MueblePiezas1> piezasNecesarias;
            String mueble = datosLinea[0]; 
            String usuario = datosLinea[1];
            String fecha = datosLinea[2];
            double precioCosto=0;
            double precioVenta;
            Mueble muebleUsar;
            String identificador;
            int cantidadPiezas;
            boolean existenPiezasSuficientes=true;
            //valida si se puede o no
            boolean cumpleUsuario=validaciones.claseUsuario(usuario, "fabrica");
            piezasNecesarias = cargador.cargarMueblePiezas(mueble);
            for(int i=0;i<piezasNecesarias.size();i++){
                for(int j=0;j<piezasNecesarias.get(i).getCantidadPieza();j++){
                    
                }
                if(validaciones.existenPiezasSuficientes(piezasNecesarias.get(i).getNombrePieza(), piezasNecesarias.get(i).getCantidadPieza())==false){
                    existenPiezasSuficientes=false;
                    break; 
                }

            }// si existen piezas suficientes entra
            if(cumpleUsuario==true && existenPiezasSuficientes==true){
                
                for(int i=0;i<piezasNecesarias.size();i++){
                        Pieza[] piezaAUsar= new Pieza[piezasNecesarias.get(i).getCantidadPieza()];
                        for(int j=0;j<piezasNecesarias.get(i).getCantidadPieza();j++){
                            piezaAUsar[j] = cargador.cargarPieza(piezasNecesarias.get(i).getNombrePieza());
                                piezaAUsar[j].setEstadoPieza(false);
                                precioCosto+=piezaAUsar[j].getPrecio();
                                guardador.guardarPieza(piezaAUsar[j], Integer.toString(piezaAUsar[j].getIdentificadorUnico()));
                        }
                }
                // asgina valores y crea el mueble
                identificador=validaciones.asignarIdentificadorUnico();
                muebleUsar = cargador.cargarMueble(mueble);
                precioVenta = muebleUsar.getPrecio();
                muebleEnsamblado= new EnsamblarMueble(mueble, usuario, fecha, identificador, precioCosto, precioVenta);
                guardador.guardarMuebleEnsamblado(muebleEnsamblado);
                datos.introducirDatosALaLista("Se ha ensamblado el mueble: "+mueble+" correctamente");
            }else{
                if(cumpleUsuario==false && existenPiezasSuficientes==true){
                    datos.introducirDatosALaLista("Error Logico: El usuario "+usuario+" no pertenece al area pertinente");
                }else{
                    datos.introducirDatosALaLista("Error Logico: No existen piezas suficientes para armar el mueble: "+mueble);
                }
            }
            }catch(NullPointerException e){
                System.out.println("NO se puede crear mueble, no existe");
            }
                
        }
    }
}

// aqui hay que verificar si cumple las normas de sintaxis y los limites cada linea del archivo a procesar
// se debe de guardar la informacion del proceso de carga en un archivo con un formato legible
// crear los archivos de salida     
// logica que implementaba algo mas en mueble piezas pero que al final no se uso por x motivos, pero no se queria eliminar por si llega a ser util en algun momento

        
//        if(cadenaAVerificar.equals(ORDENES[3])){
//            String nombreMueble;
//            MueblePiezas mueblePiezasAGuardar;
//            
//            try {
//                
//                String[] piezasAUsar = new String[datosLinea.length-1];
//                int[] numeroPiezaUsar= new int[piezasAUsar.length/2];
//
//                String[] piezaNombre = new String[numeroPiezaUsar.length];
//                int[] cantidadPieza= new int[numeroPiezaUsar.length];
//                for(int i=0;i<numeroPiezaUsar.length;i++){
//                    int k=1;
//                    piezaNombre[i]= datosLinea[k];
//                    cantidadPieza[i]=Integer.parseInt(datosLinea[k+1]);
//                    k+=2;
//                }
//                
//                //int piezasDiferentes = piezasAUsar.length/2;
//                nombreMueble = datosLinea[0];
//                boolean existeMueble = validaciones.verificarMueble(nombreMueble);
//                boolean existenPiezas=true;
//                boolean bandera=true;
//                for(int i=0;i<piezaNombre.length;i++){
//                    
//                    if(cargador.existePieza(piezaNombre[i])==false){
//                        bandera=false;
//                    }
//                    i++;
//                }
//                
//                for(int l=0;l<piezasAUsar.length;l++){
//                    piezasAUsar[l]=datosLinea[l+1];
//                }
//                if(bandera==false){
//                    existenPiezas=false;
//                }
//                
//                if(existeMueble==true && existenPiezas==true){
//                    mueblePiezasAGuardar = new MueblePiezas(nombreMueble, numeroPiezaUsar.length, piezasAUsar);
//                    datos.introducirDatosALaLista("Se le asignaron las piezas al mueble "+nombreMueble+" correctamente");
//                    guardador.guardarMueblePiezas(mueblePiezasAGuardar);
//                }
//                
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

// si es ensamblar un mueble
        
        
//        if(cadenaAVerificar.equals(ORDENES[4])){
//            EnsamblarMueble muebleEnsamblado;
//            try {
//                String mueble = datosLinea[0]; 
//                String usuario = datosLinea[1];
//                String fecha = datosLinea[2];
//                double precioCosto=0;
//                double precioVenta;
//                Mueble muebleUsar;
//                String identificador;
//                int cantidadPiezas;
//                boolean existenPiezasSuficientes=true;
//                boolean cumpleUsuario=validaciones.claseUsuario(usuario, "fabrica");
//                
//                MueblePiezas piezasNecesarias = cargador.cargarMueblePiezas(mueble);
//                for(int i=0;i<piezasNecesarias.getNombrePieza().length;i++){
//                    
//                    if(validaciones.existenPiezasSuficientes(piezasNecesarias.getNombrePieza()[i], piezasNecesarias.getCantidadPieza()[i])==false){
//                       existenPiezasSuficientes=false;
//                        break; 
//                    }
//                        
//                }
//                
//                if(cumpleUsuario==true && existenPiezasSuficientes==true){
//                    
//
//                    for(int i=0;i<piezasNecesarias.getNombrePieza().length;i++){
//                        Pieza[] piezaAUsar= new Pieza[piezasNecesarias.getCantidadPieza()[i]];
//                        for(int j=0;j<piezasNecesarias.getCantidadPieza()[i];j++){
//                            piezaAUsar[j] = cargador.cargarPieza(piezasNecesarias.getNombrePieza()[i]);
//                            piezaAUsar[j].setEstadoPieza(false);
//                            precioCosto+=piezaAUsar[j].getPrecio();
//                            guardador.guardarPieza(piezaAUsar[j], Integer.toString(piezaAUsar[j].getIdentificadorUnico()));
//                        }
//                       
//                    }
//                    
//                    identificador=validaciones.asignarIdentificadorUnico();
//                    muebleUsar = cargador.cargarMueble(mueble);
//                    precioVenta = muebleUsar.getPrecio();
//                    muebleEnsamblado= new EnsamblarMueble(mueble, usuario, fecha, identificador, precioCosto, precioVenta);
//                    guardador.guardarMuebleEnsamblado(muebleEnsamblado);
//
//                }  
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//      