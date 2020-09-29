
package Logica;

import Datos.Cliente;
import Datos.ControlIdentificadorMueble;
import Datos.EnsamblarMueble;
import Datos.Factura;
import Datos.Mueble;
import Datos.MueblePiezas;
import Datos.MueblePiezas1;
import Datos.Pieza;
import Datos.Usuario;
import InterfazGrafica.CargaDatos1;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Validaciones {
    // atributos de la clase
    private final int LIMITE_CARACTERES_1=100;
    private final int LIMITE_CARACTERES_2=50;
    private final int LIMITE_MINIMO_CONTRASEÑA=6;
    private SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private CargaDatos1 datos= null;
    private CargarArchivo carga;
    private static GuardarArchivo guardador = new GuardarArchivo();
    // constructor
    public Validaciones(){
        carga = new CargarArchivo();
        
    }// sobbrecarga del constructor 
    public Validaciones(CargaDatos1 datos){
        this.datos=datos;
        carga = new CargarArchivo();
    }
    // introduce datos a la lista 
    public void introducirDatosLista(String mensaje){
        if(datos==null){
            // dependiendo de que constructor se usoo para inicializar añadira a la lista o no 
        }else{
            datos.introducirDatosALaLista(mensaje);
        }
    }
    // crea un usuario nuevo, ademas valida
    public boolean crearUsuario(String nombre, String password){
        boolean crearUsuario=true;
        if(verificarNombre(nombre)!=true){
            crearUsuario=false;// si el usuario ya existe dice que no se puede 
            introducirDatosLista("Error Logico: El usuario "+nombre+" ya existe");
            return crearUsuario;
        }// verifica si cumple la contraseña 
        if(verificarSiCumpleConLimiteCaracteres(password, LIMITE_MINIMO_CONTRASEÑA, 2)==false){
            crearUsuario=false;
            introducirDatosLista("Error Formato: La contraseña no cumple el limite minimo de caracteres: "+LIMITE_MINIMO_CONTRASEÑA);
            return crearUsuario;
        }// si cumple devuelve que si se puede 
        return crearUsuario;
    }
// verifica el area de un usuario
    public boolean verificarArea(String area){
        try{
            int numeroArea = Integer.parseInt(area);
            if(numeroArea<4 && numeroArea>0){
                return true;
            }else{// si no esta en el rango muestra el error 
                JOptionPane.showMessageDialog(null, "El numero no esta en el rango de 1-3");
                return false;
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "No es un numero lo que esta en el campo de area");
            return false;
        }
    }
    // verifica el nombre de usuario 
    public boolean verificarNombre(String nombre){
        Usuario usuario = null;
        usuario = carga.cargarUsuario(nombre);
        if(usuario!=null){
            System.out.println("Ya existe ese usuario");
            return false;
        }else{
            return true;
        }
    }
    // verifica si existe un mueble 
    public boolean verificarMueble(String nombre){
        Mueble mueble;
        boolean existeMueble=true;
        // carga un mueble
        mueble=carga.cargarMueble(nombre);
        if(mueble!=null){  
        }else{// si el mueble no es null seria falso 
            existeMueble=false;
        }
        return existeMueble;
    }
    // verifica si existe la pieza que se quiere añadir
    public boolean verificarExistePieza(String nombre){
        Pieza pieza;
        //puede existir un problema puede ser mejor usar el metodo de existePieza de cargarArchivo
        pieza= carga.cargarPieza(nombre);
        if(pieza!=null){
            return true;
        }else{
            return false;
        }
    }

    // modifique algo del usuario
    public boolean verificarUsuario(String nombre, String password){// desactiva al usuario
        Usuario usuario = carga.cargarUsuario(nombre);
        if(usuario!=null){
            if(usuario.getPassword().equals(password)){
                if(usuario.isEstaActivo()==true){
                    return true;
                }else{// verifica si el usuario puede ingresar al sistema
                    JOptionPane.showMessageDialog(null, "El usuario fue desactivado del sistema");
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    // asigna el identificador unico a un mueble 
    public String asignarIdentificadorUnico() throws IOException{
        String valorAsignado=null;
        ControlIdentificadorMueble identificador = carga.cargarIdentificadorMueble();
        valorAsignado= Integer.toString(identificador.getNumeroMueble());
        identificador.setNumeroMueble(identificador.getNumeroMueble()+1);
        guardador.guardarIdentificadorMueble(identificador);

        return valorAsignado;
    }
    
    // asigna la clase de usuario que sera 
    public boolean claseUsuario(String nombreUsuario, String area){
        Usuario usuario = carga.cargarUsuario(nombreUsuario);
        if(usuario!=null){
            if(usuario.getArea().equals(area)){
               if(usuario.isEstaActivo()==true){
                   return true;
               }else{
                   return false;
               }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    // existen piezas suficientes para un mueble 
    public boolean existenPiezasSuficientes(String nombrePieza, int cantidad){
        int piezas;
        boolean siExisten=false;
        if(carga.cantidadPiezas(nombrePieza)>=cantidad){
            siExisten=true;
        }// si cumple  manda que si sino que no 
        return siExisten;
    }
    // verifica si cumple el limite de caracteres 
    public static boolean verificarSiCumpleConLimiteCaracteres(String caracteres, int limiteCaracteres, int tipoLimite){
        
        if(tipoLimite==1){
            if(caracteres.length()> limiteCaracteres){
                return false;
            }else{
                return true;
                //hacer algo si se valida correctamente el numero de caracteres
            }
        }else{
            if(caracteres.length()< limiteCaracteres){
                return false;
                
            }else{
                return true;
            }
        }
    }
    // manda el arreglo enviado ya sin comillas y en un arreglo
    public static String[] quitarComillasALaLinea(String lineaTexto){
        int posicion1;
        String texto;
        // busca donde esta el ultimo parentesis
        posicion1 = lineaTexto.lastIndexOf(")");
        //guarda la posicion
        texto=lineaTexto.substring(1, posicion1);
        // genera el arreglo 
        String[] atributosDeLinea = texto.split(",");
        int posicion;
        // segun el arreglo quita las comillas
        for (int i = 0; i < atributosDeLinea.length; i++) {
            if(atributosDeLinea[i].contains("\"")){
                posicion = atributosDeLinea[i].lastIndexOf("\"");
                atributosDeLinea[i] = atributosDeLinea[i].substring(1, posicion);
            }
            
        }        
        return atributosDeLinea;
    }
    // verifica si se puede ensamblar un mueble 
    public void ensamblarMueble(String mueble1, String usuario1, String fecha1){
            EnsamblarMueble muebleEnsamblado;
            try{// es un arraylist porque nunca va a recibir muchas piezas
            ArrayList<MueblePiezas1> piezasNecesarias;
            String mueble = mueble1; 
            String usuario = usuario1;
            String fecha = fecha1;
            double precioCosto=0;
            double precioVenta;
            Mueble muebleUsar;
            String identificador;
            int cantidadPiezas;
            boolean existenPiezasSuficientes=true;
            boolean cumpleUsuario=claseUsuario(usuario, "fabrica");
            // mira si existen las piezas necesarias para ensamblar
            piezasNecesarias = carga.cargarMueblePiezas(mueble);
            for(int i=0;i<piezasNecesarias.size();i++){
                for(int j=0;j<piezasNecesarias.get(i).getCantidadPieza();j++){
                }// si existen las piezas suficientes manda que si, sino que no 
                if(existenPiezasSuficientes(piezasNecesarias.get(i).getNombrePieza(), piezasNecesarias.get(i).getCantidadPieza())==false){
                    existenPiezasSuficientes=false;
                    break; 
                }
            }// si cumple todo entra a ensamblar el mueble 
            if(cumpleUsuario==true && existenPiezasSuficientes==true){
                // ciclo para las piezas necesarias 
                for(int i=0;i<piezasNecesarias.size();i++){
                        Pieza[] piezaAUsar= new Pieza[piezasNecesarias.get(i).getCantidadPieza()];
                        for(int j=0;j<piezasNecesarias.get(i).getCantidadPieza();j++){
                            piezaAUsar[j] = carga.cargarPieza(piezasNecesarias.get(i).getNombrePieza());
                                piezaAUsar[j].setEstadoPieza(false);
                                // va añadiendo el precio costo del mueble 
                                precioCosto+=piezaAUsar[j].getPrecio();
                                guardador.guardarPieza(piezaAUsar[j], Integer.toString(piezaAUsar[j].getIdentificadorUnico()));
                        }
                }
                // crea el mueble ensamblado
                identificador=asignarIdentificadorUnico();
                muebleUsar = carga.cargarMueble(mueble);
                precioVenta = muebleUsar.getPrecio();
                muebleEnsamblado= new EnsamblarMueble(mueble, usuario, fecha, identificador, precioCosto, precioVenta);
                guardador.guardarMuebleEnsamblado(muebleEnsamblado);// guarda el mueble ensamblado y lo muestra 
                JOptionPane.showMessageDialog(null,"Se ensamblo correctamente el mueble "+muebleEnsamblado.getNombre());
            }else{
                JOptionPane.showMessageDialog(null, "No se puede crear faltan piezas");
            }// si existen erroes dice que no se pudo 
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "No existe el mueble");
            } catch (IOException ex) {
                ex.printStackTrace();
        }        
    }
    // valida los datos de compra 
    public boolean validarDatosCompra(String[] datos){
        boolean sePuede=true;
        Cliente cliente=null;
        EnsamblarMueble mueble = null;
        for(int i =0; i<datos.length;i++){// si existen casillas vacias dice que hace falta 
            if(datos[i].equals("") || datos[i]==null){
                JOptionPane.showMessageDialog(null, "Hay casillas vacias, por favor llenalos correctamente");
                return false;
            }
        }// si se puede entra 
        try{// carga el cliente 
        cliente=carga.cargarCliente(Integer.parseInt(datos[0]));
        if(cliente==null){// si no esta registrado el usuario dice que no esta en el sistema
            JOptionPane.showMessageDialog(null, "No esta registrado el usuario, registralo antes de hacer la compra");
            return false;
        }else{
            boolean nombre = datos[1].equals(cliente.getNombre());
            boolean direccion = datos[2].equals(cliente.getDireccion());
            if(nombre==true && direccion==true){
                
            }else{// si los datos del cliente no estan bien lo muestraa
                JOptionPane.showMessageDialog(null, "Los datos del cliente no estan bien");
                sePuede=false;
                return false;
                
            }
        }// carga un mueble por medio del identificador 
        mueble = carga.cargarMueblePorIdentificador(Integer.parseInt(datos[3]));
        if(mueble==null){// muestra si no existe el mueble 
            JOptionPane.showMessageDialog(null, "Introduciste un mueble con identificador incorrecto");
            sePuede=false;
            return false;
        }else{// si existe rellena las casillas
            boolean nombreMueble = datos[4].equals(mueble.getNombre());
            boolean total = datos[5].equals(Double.toString(mueble.getPrecioVenta()));
            if(nombreMueble==true && total==true && mueble.isEstadoMueble()==true){
                // si cumple la compra entra y retorna que se puede 
                return true;
            }else{// si algo esta mal los datos estarian mal 
                JOptionPane.showMessageDialog(null, "Los datos del mueble estan mal");
                sePuede=false;
                return false;
            }
        }
        }catch(Exception e){
            sePuede=false;
            
        }
        return sePuede;
    }
    // valida los espacios si estan vacios o no 
    public boolean validarEspaciosUsuario(String[] datos){
        for(int i =0; i<datos.length;i++){
            if(datos[i].equals("") || datos[i]==null){
                JOptionPane.showMessageDialog(null, "Hay casillas vacias, por favor llenalos correctamente");
                return false;
            }
        }
        return true;
    }
    // registra un cliente nuevo en el sistema 
    public void registrarCliente(int NIT, String nombre, String direccion){
        Cliente cliente=null;
        cliente = carga.cargarCliente(NIT);
        if(cliente ==null){
            cliente = new Cliente(NIT, nombre, direccion);
            guardador.guardarCliente(cliente);// si crea un usuario nuevo muestra
            JOptionPane.showMessageDialog(null, "Se registro el usuario correctamente");
        }else{// si ya existia dice eso
            JOptionPane.showMessageDialog(null, "El usuario ya se registro");
        }
        
    }
    // registra la factura 
    public boolean registarFactura(String[] datos, String fecha, String noFactura, String usuarioVenta, double precioCosto){
        Factura factura;
        Date fechaFormateada = null;
        int NIT=0;
        int numeroFactura =0;
        double total=0;
        try{// verifica si existe el mueble 
            NIT = Integer.parseInt(datos[0]);
            numeroFactura = Integer.parseInt(noFactura);
            total= Double.parseDouble(datos[5]);
            fechaFormateada = formato.parse(fecha);
            // verifica los datos del cliente 
            // crea la factura 
            factura = new Factura(NIT, datos[1],datos[2], fechaFormateada,numeroFactura, datos[4], datos[3],total,usuarioVenta, precioCosto );
            guardador.guardarFactura(factura);// guarda la factura 
            JOptionPane.showMessageDialog(null, "Se registro la factura correctamente");
            return true;

            // si existe un error de formato lo muestra
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "No se puede crear la factura, los datos estan mal");
            ex.printStackTrace();
            return false;
        }

    }
    // desactiva un usuario segun su objeto
    public boolean desactivarUsuario(Usuario usuario){
        if(usuario!=null){// guarda que se desactivo 
            usuario.setEstaActivo(false);
            guardador.guardarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "Se desactivo el usuario: "+usuario.getNombre());
            return true;
        }else{
            return false;
        }
    }
    // carga el controlador de muebles 
    public int numeroMueble(ArrayList<ControladorMuebleVendido> controlador, String nombre ){
        int casilla = -1;
        for(int i=0; i<controlador.size();i++){
            if(controlador.get(i).getNombreMueble().equals(nombre)){
                return i;
            }
        }
        return casilla;
    }

}
// LOGICA QUE SE PUDO UTLIZAR AL ENSAMBLAR UN MUEBLE PERO QUE YA NO SE UTILIZO, PUEDE SERVIR YA QUE ES UN TANTO DIFERENTE 
//    public boolean ensamblarMueble(String mueble){
//        boolean sePuedeEnsamblar=true;
//        
//        try{
//           
//            double precioCosto=0;
//            int cantidadPiezas;
//            ArrayList<MueblePiezas1> piezas = carga.cargarMueblePiezas(mueble);
//            for(int j=0;j<piezas.size();j++){
//            for(int i=0;i<piezas.get(j).getCantidadPieza();i++){
//                Pieza[] piezaAUsar= new Pieza[piezas.getCantidadPieza()[i]];
//            
//                cantidadPiezas = piezas.getCantidadPieza()[i];
//                for(int j=0;j<piezaAUsar.length;j++){
//                    piezaAUsar[j] = carga.cargarPieza(piezas.getNombrePieza()[i]);
//                    if(piezaAUsar[j]==null){
//                        System.out.println("No se puede crear el mueble");
//                        return false;
//                    }
//                    
//                }
//                
//            }
//            }
//            
//        }
//        
//        catch(NullPointerException e){
//            System.out.println("No existen piezas suficientes para ensamblar");
//            sePuedeEnsamblar=false;
//            e.printStackTrace();
//        }
//        
//        return sePuedeEnsamblar;
//        
//    }