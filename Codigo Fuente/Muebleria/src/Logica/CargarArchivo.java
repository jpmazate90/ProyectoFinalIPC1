package Logica;

import Datos.Cliente;
import Datos.ControlIdentificadorMueble;
import Datos.ControlNumeroDevoluciones;
import Datos.ControlNumeroFacturas;
import Datos.ControlNumeroPiezas;
import Datos.Devolucion;
import Datos.EnsamblarMueble;
import Datos.Factura;
import Datos.Mueble;
import Datos.MueblePiezas1;
import Datos.Pieza;
import Datos.Usuario;
import InterfazGrafica.EditarPiezas;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

public class CargarArchivo {
    // atributos de la clase
    private static Validaciones validador = new Validaciones();
    private static ControlNumeroPiezas control= new ControlNumeroPiezas();
    private static ControlIdentificadorMueble muebleUsar= new ControlIdentificadorMueble();
    private static ControlNumeroDevoluciones devoluciones = new ControlNumeroDevoluciones();
    private static GuardarArchivo guardador= new GuardarArchivo();
    private static ControlNumeroFacturas controlFacturas = new ControlNumeroFacturas();
    private SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
    private final int INTERVALO_PERDIDA = 3;
    private EditarPiezas editarPiezas;
    // constructor
    public CargarArchivo(){
    }
    // constructor
    public CargarArchivo(EditarPiezas editarPiezas){
        this.editarPiezas= editarPiezas;
    }
    // carga un usuario 
    public Usuario cargarUsuario(String nombreUsuario){
        Usuario usuarioPedido=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con solicitud y el numero la carga
                if(nombreArchivo.startsWith("usuario"+nombreUsuario) && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        usuarioPedido = (Usuario) inputStream.readObject();
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                        System.out.println(" No es un usuario");
                    }
                }
            }
        }
        return usuarioPedido;
    }
    // modifique la busqueda puede causar error
    public Mueble cargarMueble(String mueble){
        Mueble mueblePedido=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si mueble carga
                if(nombreArchivo.startsWith("mueble"+mueble+".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        mueblePedido = (Mueble) inputStream.readObject();
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }  
        }
        return mueblePedido;
    }
   // carga el controlador de piezas 
    public ControlNumeroPiezas cargarContadorPiezas() throws IOException{
            File file = new File("contadorPiezas.jp");
            if(!file.exists()){
                file.createNewFile();
                guardador.guardarContadorPieza(control);
            }
            ControlNumeroPiezas numeroPiezas= null;
            try {// lo manda
                FileInputStream input = new FileInputStream(file);
                ObjectInputStream objectInput = new ObjectInputStream(input);
                numeroPiezas = (ControlNumeroPiezas) objectInput.readObject();
   
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        return numeroPiezas;
    }
    // puede existir un error al comprobar que exista la pieza
    public Pieza cargarPieza(String nombrePieza){
        Pieza pieza=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con pieza entra
                if(nombreArchivo.startsWith("pieza"+nombrePieza) && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        pieza = (Pieza) inputStream.readObject();
                        if(pieza.isEstadoPieza()==false){
                            pieza=null;
                        }else{
                            break;
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
        return pieza;
        }
    // carga las piezas necesarias para un mueble especifico
    public ArrayList<MueblePiezas1> cargarMueblePiezas(String mueble){
        ArrayList<MueblePiezas1> coleccionPiezas= new ArrayList<MueblePiezas1>();
        MueblePiezas1 pieza;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                if(nombreArchivo.startsWith("mueblepiezas"+mueble) && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        pieza = (MueblePiezas1) inputStream.readObject();
                        coleccionPiezas.add(pieza);
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
        return coleccionPiezas;
    }
    // carga las piezas a una tabla
    public void cargarPiezaTabla(DefaultTableModel tabla){
        Pieza pieza=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con solicitud y el numero la carga
                if(nombreArchivo.startsWith("pieza") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        pieza = (Pieza) inputStream.readObject();
                        if(pieza.isEstadoPieza()==false){
                        }else{// si el estado de la pieza no es falso sigue 
                            String[] piezaAUsar = new String[3];
                            piezaAUsar[0] = pieza.getNombre();
                            piezaAUsar[1] = Double.toString(pieza.getPrecio());
                            piezaAUsar[2] = Integer.toString(pieza.getIdentificadorUnico());
                            tabla.addRow(piezaAUsar);
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }     
        }
    }
    // carga el nombre de las piezas, y las manda a la tabla
    public void cargarNombrePiezas(DefaultTableModel tabla){
        Pieza pieza;
        ArrayList<String> nombrePiezas = new ArrayList<String>();
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if (folder.isDirectory()) {
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con pieza entra
                if (nombreArchivo.startsWith("pieza") && nombreArchivo.endsWith(".jp")) {
                    File archivoNuevo = new File(nombreArchivo);
                    try (FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                            ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);) {
                        pieza = (Pieza) inputStream.readObject();
                        boolean flag=true;
                        for(int i=0;i<nombrePiezas.size();i++){
                            if(nombrePiezas.get(i).equals(pieza.getNombre())){
                                flag=false;
                            }
                        }// si la bandera es true, es decir que es una pieza nueva la agrega
                        if(flag==true){
                            nombrePiezas.add(pieza.getNombre());
                            cargarPiezasInformacion(tabla, pieza.getNombre());
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ef) {
                    }
                }
            }
        }
    }
    private static final int NUMERO_MAXIMO_VENTA=100000000;
// controlador del identificador de un mueble
    public ControlIdentificadorMueble cargarIdentificadorMueble() throws IOException {
        ControlIdentificadorMueble mueble = null;
        File file = new File("identificadormueble.jp");
        if (!file.exists()) {// si es la primera vez crea uno nuevo desde cero
            file.createNewFile();
            guardador.guardarIdentificadorMueble(muebleUsar);
        }
        try {
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(input);
            mueble = (ControlIdentificadorMueble) objectInput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mueble;
    }
    // carga la pieza a la tabla
    public void cargarPiezasInformacion(DefaultTableModel tabla, String nombre){
        Pieza pieza=null;
        int contadorPiezas=0;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if (folder.isDirectory()) {
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con pieza
                if (nombreArchivo.startsWith("pieza"+nombre) && nombreArchivo.endsWith(".jp")) {
                    File archivoNuevo = new File(nombreArchivo);
                    try (FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                            ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);) {
                        pieza = (Pieza) inputStream.readObject();
                        if (pieza.isEstadoPieza() == false) {
                        } else {// carga el contador de las piezas
                            contadorPiezas++;
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ef) {
                    }
                }
            }
            // añade a la tabla
            String[] datos = new String[2];
            datos[0]= nombre;
            datos[1]= Integer.toString(contadorPiezas);
            tabla.addRow(datos);
        }
    }
    // carga un mueble ensamblado
    public EnsamblarMueble cargarMuebleEnsamblado(String mueble){
        EnsamblarMueble mueblePedido=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con mueble ensamblado y el nombre del mueble especificado entra
                if(nombreArchivo.startsWith("muebleEnsamblado"+mueble) && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        mueblePedido = (EnsamblarMueble) inputStream.readObject();
                        if(mueblePedido.isEstadoMueble()==false){
                            return null;
                        }
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
        return mueblePedido;
    }
    // si existe la pieza retorna que si 
    public boolean existePieza(String nombre){
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con pieza
                if(nombreArchivo.startsWith("pieza"+nombre) && nombreArchivo.endsWith(".jp")){
                    return true;
                }  
            }
        }
        return false;    
    }
    // regresa la cantidad de piezas que tiene esa pieza
    public int cantidadPiezas(String nombre){
        Pieza pieza;
        File folder = new File(".");
        int contador=0;
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con pieza y el parametro entra
                if(nombreArchivo.startsWith("pieza"+nombre) && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        pieza = (Pieza) inputStream.readObject();
                        if(pieza.isEstadoPieza()==false){
                            pieza=null;
                        }else{
                            contador++;
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }  
            }
        }
        return contador;
    }
    // carga una pieza especcifica
    public Pieza cargarPiezaEspecifica(String nombrePieza, String identificador){
        Pieza pieza=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con pieza y el identificador entra
                if(nombreArchivo.startsWith("pieza"+nombrePieza+identificador) && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        pieza = (Pieza) inputStream.readObject();
                        if(pieza.isEstadoPieza()==false){
                            // si el estado de la pieza esta usada manda null
                            pieza=null;
                        }else{
                            break;
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
                
            }
        }
        return pieza;
        }
    // elimina una pieza, si asi se desea
    public void eliminarPieza(Pieza pieza, String identificador){
        File folder = new File(".");
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con solicitud y el numero la carga
                if(nombreArchivo.startsWith("pieza"+pieza.getNombre()+identificador) && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    archivoNuevo.delete();
                } 
            }
        }   
    }
    // carga los muebles disponibles a una lista
    public void cargarMuebleslista(DefaultListModel lista){
        Mueble mueblePedido=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con mueble entra
                if(nombreArchivo.startsWith("mueble") && nombreArchivo.endsWith(".jp") && nombreArchivo.contains("piezas")==false && nombreArchivo.contains("Ensamblado")==false ){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        mueblePedido = (Mueble) inputStream.readObject();
                        // agrega a la lista
                        lista.addElement(mueblePedido.getNombre());
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
    }
    // carga todos los muebles ensamblados que se han creado sin importar si esta vendido o no
    public EnsamblarMueble cargarTodosMueblesEnsamblados(DefaultTableModel modelo){
        EnsamblarMueble mueblePedido=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con mueble ensamblado
                if(nombreArchivo.startsWith("muebleEnsamblado") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        mueblePedido = (EnsamblarMueble) inputStream.readObject();
                        String[] datos = new String[6];
                        // agrega a la tabla
                        datos[0] = mueblePedido.getFecha();
                        datos[1] = mueblePedido.getIdentificador();
                        datos[2] = mueblePedido.getNombre();
                        datos[3] = mueblePedido.getUsuario();
                        datos[4] = Double.toString(mueblePedido.getCosto());
                        datos[5] = Boolean.toString(mueblePedido.isEstadoMueble());
                        modelo.addRow(datos);
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
        return mueblePedido;
    }
    // carga un cliente por su nit
    public Cliente cargarCliente(int nit){
        Cliente cliente=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con cliente y el nit entra
                if(nombreArchivo.startsWith("cliente"+nit+".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        cliente = (Cliente) inputStream.readObject();
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
        return cliente;
    }
    // carga un mueble ensamblado por identificador
    public EnsamblarMueble cargarMueblePorIdentificador(int identificador){
        EnsamblarMueble mueble=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con mueble ensamblado entra
                if(nombreArchivo.startsWith("muebleEnsamblado") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        mueble = (EnsamblarMueble) inputStream.readObject();
                        if(mueble.isEstadoMueble()==false){// si el estado del mueble es falso no entra
                            mueble=null;
                        }else{// luego comprara si el mueble es el indicado
                            if(mueble.getIdentificador().equals(Integer.toString(identificador))){
                                return mueble;
                            }else{
                                mueble=null;
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
        return mueble;
    }
    // carga el contador de las facturas
    public ControlNumeroFacturas cargarContadorFacturas() throws IOException{
            File file = new File("contadorFacturas.jp");
            if(!file.exists()){
                file.createNewFile();// si  no existe crea uno nuevo desde cero
                guardador.guardarContadorFactura(controlFacturas);
            }
            ControlNumeroFacturas numeroFacturas= null;
            try {
                FileInputStream input = new FileInputStream(file);
                ObjectInputStream objectInput = new ObjectInputStream(input);
                numeroFacturas = (ControlNumeroFacturas) objectInput.readObject();
            } catch (Exception e) {
                e.printStackTrace();   
            }
        return numeroFacturas;
    }
    // carga una factura segun su numero 
    public  Factura cargarFactura(int numeroFactura){
        Factura factura=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con factura y el numero entra
                if(nombreArchivo.startsWith("factura"+numeroFactura+".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }            
        }//regresa la factura solicitada si no existe null
        return factura;
    }
    // carga un mueble vendido por medio del identificador
    public EnsamblarMueble cargarMuebleVendido(int identificador){
        EnsamblarMueble mueble=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con mueble ensamblado entra
                if(nombreArchivo.startsWith("muebleEnsamblado") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        mueble = (EnsamblarMueble) inputStream.readObject();
                        // verifica si es el indicado mueble
                            if(mueble.getIdentificador().equals(Integer.toString(identificador))){
                                return mueble;
                            }else{
                                mueble=null;
                            }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }// regresa el muebe o null
        return mueble;
    }
    // carga una devolucion por medio del numero de factura y del cliente
    public  Devolucion cargarDevolucion(int numeroFactura, String nombreCliente){
        Devolucion devolucion=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con solicitud y el numero la carga
                if(nombreArchivo.startsWith("devolucion"+nombreCliente) && nombreArchivo.endsWith(numeroFactura+".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        devolucion = (Devolucion) inputStream.readObject();
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }            
        }
        return devolucion;
    }
    
    // Carga el controlador de devoluciones si no existe crea uno nuevo
    public ControlNumeroDevoluciones cargarContadorDevoluciones() throws IOException{
            File file = new File("contadorDevoluciones.jp");
            if(!file.exists()){// aqui crea uno nuevo
                file.createNewFile();
                guardador.guardarControladorDevoluciones(devoluciones);
            }// si ya existe carga el contador
            ControlNumeroDevoluciones numeroDevoluciones= null;
            try {
                FileInputStream input = new FileInputStream(file);
                ObjectInputStream objectInput = new ObjectInputStream(input);
                numeroDevoluciones = (ControlNumeroDevoluciones) objectInput.readObject();
            } catch (Exception e) {
                e.printStackTrace();   
            }// regresa el nuero de devolucion
        return numeroDevoluciones;
    }
    // refresca el modelo de la tabla
    public void refrescarModelo(DefaultTableModel modelo){
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
        }
    }
    // carga las facturas de un cliente especifico 
    public void cargarFacturasCliente(String cliente, DefaultTableModel modelo){
        refrescarModelo(modelo);
        Factura factura=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza confactura entra
                if(nombreArchivo.startsWith("factura") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();// compara si son igual con el cliente del parametro
                        if(factura.getNombreCliente().equals(cliente)){
                            String datos[] = new String[5];
                            datos[0]= formato.format(factura.getFechaCompra());
                            datos[1]= factura.getIdentificador();
                            datos[2]= factura.getNombreMueble();
                            datos[3]= Double.toString(factura.getTotal());
                            datos[4]= Integer.toString(factura.getNumeroFactura());// agrega a la tabla
                            modelo.addRow(datos);
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }            
        }
    }
    // carga las devoluciones de un client especifico
    public void cargarDevolucionesCliente(String cliente, DefaultTableModel modelo){
        refrescarModelo(modelo);
        Devolucion devolucion=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con devolucion 
                if(nombreArchivo.startsWith("devolucion") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        devolucion = (Devolucion) inputStream.readObject();
                        if(devolucion.getNombreCliente().equals(cliente)){// compara si es del cliente que se envio como parametro
                            String datos[] = new String[6];
                            datos[0]= formato.format(devolucion.getFechaCompra());
                            datos[1]= formato.format(devolucion.getFechaDevolucion());
                            datos[2]= devolucion.getIdentificador();
                            datos[3]= devolucion.getMuebleDevuelto();
                            datos[4]= Double.toString(devolucion.getPrecioVenta());
                            datos[5]= Integer.toString(devolucion.getNumeroFactura());
                            modelo.addRow(datos);// agrega a la tabla
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }            
        }
    }
    // carga los muebles ensamblados disponibles en una tabla
    public void cargarMueblesTabla(DefaultTableModel tabla){
        EnsamblarMueble mueble=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con solicitud y el numero la carga
                if(nombreArchivo.startsWith("muebleEnsamblado") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        mueble = (EnsamblarMueble) inputStream.readObject();
                        if(mueble.isEstadoMueble()==false){
                        }else{// // crea el arreglo
                            String[] datos = new String[5];
                            datos[0] = mueble.getIdentificador();
                            datos[1] = mueble.getNombre();
                            datos[2] = formato.format(mueble.getFechaEnFormato());
                            datos[3] = mueble.getUsuario();
                            datos[4] = Double.toString(mueble.getPrecioVenta());
                            tabla.addRow(datos);// añade a la tabla
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }     
        }
    }
    // carga las ventas del dia 
    public void cargarVentasDia(DefaultTableModel tabla, String fechaActual){
        Factura factura=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con factura entra
                if(nombreArchivo.startsWith("factura") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();
                        String fechaFactura = formatoSinHora.format(factura.getFechaCompra());
                        if(fechaFactura.equals(fechaActual)){// compara la fecha de compra de la factura
                            String[] datos = new String[6];// con la actual si es la actual entra
                            datos[0] = factura.getNombreMueble();
                            datos[1] = factura.getIdentificador();
                            datos[2] = Double.toString(factura.getTotal());
                            datos[3] = factura.getUsuarioVenta();
                            datos[4] = factura.getNombreCliente();
                            datos[5] = Integer.toString(factura.getNIT());
                            tabla.addRow(datos);// añade a la tabla
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }     
        }
    }
    // carga las ventas en un intevalo de tiempo
    public void cargarVentasIntervalo(DefaultTableModel tabla, Date minimo, Date maximo){
        refrescarModelo(tabla);
        Factura factura=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con factura
                if(nombreArchivo.startsWith("factura") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();
                        String fechaFactura = formatoSinHora.format(factura.getFechaCompra());
                        // compara la fecha de la factura si esta en el rango especificado
                        if(minimo.compareTo(factura.getFechaCompra())<=0 && maximo.compareTo(factura.getFechaCompra())>0){
                            String[] datos = new String[7];// si es asi entra
                            datos[0] = factura.getNombreMueble();
                            datos[1] = factura.getIdentificador();
                            datos[2] = Double.toString(factura.getTotal());
                            datos[3] = factura.getUsuarioVenta();
                            datos[4] = factura.getNombreCliente();
                            datos[5] = Integer.toString(factura.getNIT());
                            datos[6] = formato.format(factura.getFechaCompra());
                            tabla.addRow(datos);// añade a la tabla los datos
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }     
        }
    }
    // carga las devoluciones en un intervalo de tiempo
    public void cargarDevolucionesIntervalo(DefaultTableModel tabla, Date minimo, Date maximo){
        Devolucion devolucion=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con devolucion entra 
                if(nombreArchivo.startsWith("devolucion") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        devolucion = (Devolucion) inputStream.readObject();
                        String fechaFactura = formatoSinHora.format(devolucion.getFechaDevolucion());
                        // compara la fecha de la devolucion si esta en el rango especificado
                        if(minimo.compareTo(devolucion.getFechaDevolucion())<=0 && maximo.compareTo(devolucion.getFechaDevolucion())>0){
                            String[] datos = new String[6];
                            datos[0] = formato.format(devolucion.getFechaCompra());
                            datos[1] = formato.format(devolucion.getFechaDevolucion());
                            datos[2] = devolucion.getIdentificador();
                            datos[3] = devolucion.getMuebleDevuelto();
                            datos[4] = Double.toString(devolucion.getPrecioVenta());
                            datos[5] = Integer.toString(devolucion.getNumeroFactura());
                            tabla.addRow(datos);
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }     
        }
    }
    // carga un mueble segun que se especifique en el parametro
    public Mueble cargarMuebleVentas(boolean masVeces){
        Mueble muebleComparacion=null;
        Mueble muebleAMandar=null;
        int vecesVendido=NUMERO_MAXIMO_VENTA;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con mueble
                if(nombreArchivo.startsWith("mueble") && nombreArchivo.endsWith(".jp") && nombreArchivo.contains("piezas")==false && nombreArchivo.contains("Ensamblado")==false ){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        muebleComparacion = (Mueble) inputStream.readObject();
                        if(masVeces==true){// si es true carga el mueble mas vendido 
                            if(muebleComparacion.getVecesVendido()>vecesVendido){
                                vecesVendido=muebleComparacion.getVecesVendido();
                                muebleAMandar = muebleComparacion;
                            }
                        }else{// si no carga el mueble menos vendido 
                            if(muebleComparacion.getVecesVendido()<vecesVendido){
                                vecesVendido= muebleComparacion.getVecesVendido();
                                muebleAMandar = muebleComparacion;
                            }
                        }
                    } catch (IOException ex) {
                    }catch (ClassNotFoundException ef){
                    }
                }
            }
        }
        return muebleAMandar;
    }
    // carga el mueble  en un intervalo de tiempo, segun el booleano el mas vendido o el menos
    public String cargarMuebleVendido( Date minimo, Date maximo, boolean masVeces){
        ArrayList<ControladorMuebleVendido> mueblesRegistrados = new ArrayList<ControladorMuebleVendido>();
        ControladorMuebleVendido muebleControlador;
        Factura factura=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {// carga las facturas para ver el nombre del mueble
                if(nombreArchivo.startsWith("factura") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();// compara la fecha si esta en el intervalo
                        if(minimo.compareTo(factura.getFechaCompra())<=0 && maximo.compareTo(factura.getFechaCompra())>0){
                            int casilla = validador.numeroMueble(mueblesRegistrados, factura.getNombreMueble());
                            if(casilla>=0){// añade los muebles que se vendieron
                                mueblesRegistrados.get(casilla).setVecesVendido(mueblesRegistrados.get(casilla).getVecesVendido()+1);
                            }else{// si no existe ese mueble crea uno
                               muebleControlador = new ControladorMuebleVendido(factura.getNombreMueble());
                               mueblesRegistrados.add(muebleControlador);
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                } 
            }  // ordena los muebles registrados
            mueblesRegistrados = Ordenamiento.ordenamientoMueble(mueblesRegistrados);
                // dependiendo de que sea la booleana entra a uno u otro
                String nombreMueble=null;
                if(masVeces==true){// si es true al mas vendido
                    int numero = mueblesRegistrados.size()-1;
                    nombreMueble = mueblesRegistrados.get(numero).getNombreMueble();
                    return nombreMueble;
                }else{// si es false al menos vendido
                    nombreMueble = mueblesRegistrados.get(0).getNombreMueble();
                    return nombreMueble;
                }
        }
        return null;
    }
    // carga las facturas por medio del nombre del mueble 
    public void cargarFacturasPorMueble(DefaultTableModel modelo, Date minimo, Date maximo, String mueble){
        refrescarModelo(modelo);
        Factura factura=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con factura
                if(nombreArchivo.startsWith("factura") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();// compara la fecha si esta en el rango
                        if(minimo.compareTo(factura.getFechaCompra())<=0 && maximo.compareTo(factura.getFechaCompra())>0 && factura.getNombreMueble().equals(mueble)){
                            String datos[] = new String[7];// crea el arreglo
                            datos[0]= factura.getNombreMueble();
                            datos[1]= factura.getIdentificador();
                            datos[2]= Double.toString(factura.getTotal());
                            datos[3]= factura.getUsuarioVenta();
                            datos[4]= factura.getNombreCliente();
                            datos[5]= Integer.toString(factura.getNIT());
                            datos[6]= formato.format(factura.getFechaCompra());
                            modelo.addRow(datos);// añade a la tabla
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }            
        }
    }
    // carga el usuario que mas vendio en un intervalo de tiempo
    public String cargarUsuarioVentas(Date minimo, Date maximo){
        ArrayList<ControladorMuebleVendido> mueblesRegistrados = new ArrayList<ControladorMuebleVendido>();
        ControladorMuebleVendido muebleControlador;
        Factura factura=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {// si empieza con factura entra
                if(nombreArchivo.startsWith("factura") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();// compara las fechas en el intervalo
                        if(minimo.compareTo(factura.getFechaCompra())<=0 && maximo.compareTo(factura.getFechaCompra())>0){
                            int casilla = validador.numeroMueble(mueblesRegistrados, factura.getUsuarioVenta());
                            if(casilla>=0){// guarda los usuarios que vendieron en ese tiempo
                                mueblesRegistrados.get(casilla).setVecesVendido(mueblesRegistrados.get(casilla).getVecesVendido()+1);
                            }else{// si no existia el usuario en el intevalo guarda uno nuevo
                               muebleControlador = new ControladorMuebleVendido(factura.getUsuarioVenta());
                               mueblesRegistrados.add(muebleControlador);
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                } 
            }  // manda el usuario que mas vendio en ese intervalo de tiempo
            // ordena segun quien vendio mas 
            mueblesRegistrados = Ordenamiento.ordenamientoMueble(mueblesRegistrados);
            String usuario=null;// devuelve el usuario que mas vendio 
            int numero = mueblesRegistrados.size()-1;
            usuario = mueblesRegistrados.get(numero).getNombreMueble();
            return usuario;
        }
        return null;
    }
    // carga las ganancias en un intervalo de tiempo
    public double cargarFacturaGanancias(Date minimo, Date maximo){
        Factura factura=null;
        double ganancias=0;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con factura entra
                if(nombreArchivo.startsWith("factura") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        factura = (Factura) inputStream.readObject();// compara las fechas en el intervalo
                        if(minimo.compareTo(factura.getFechaCompra())<=0 && maximo.compareTo(factura.getFechaCompra())>0){
                            boolean tieneDevolucion = cargarDevolucion(factura.getIdentificador());
                            if(tieneDevolucion==true){// comprueba si tiene una devolucion esa factura 
                                double perdidaParcial= factura.getPrecioCosto()/INTERVALO_PERDIDA;
                                ganancias-=perdidaParcial;
                                // si tenia una devolucion en lugar de sumarle a las ganancias le resta
                         }else{// si no tenia devolucion añade ganancias 
                             double gananciaParcial = factura.getTotal()-factura.getPrecioCosto();
                             ganancias+=gananciaParcial;
                         }
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }    
        }
        return ganancias;
    }
    // carga una devolucion segun el identificador del mueble
    public boolean cargarDevolucion(String identificador){
        Devolucion devolucion=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con devolucion
                if(nombreArchivo.startsWith("devolucion") && nombreArchivo.endsWith(".jp")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        devolucion = (Devolucion) inputStream.readObject();// si es el identificador devuelve que si 
                        if(devolucion.getIdentificador().equals(identificador)){
                            return true;
                        }
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                    }
                }
            }     
        }
        return false;
    }
    // verifica si es la primera vez que se entra al software
    public boolean cargarPrimeraVez(){
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                if(nombreArchivo.endsWith(".jp")){
                    return false;
                }
            }
        }
        return true;
    }
    
}
