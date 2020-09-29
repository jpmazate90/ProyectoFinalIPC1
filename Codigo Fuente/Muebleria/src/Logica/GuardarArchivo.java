
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
import Datos.MueblePiezas;
import Datos.MueblePiezas1;
import Datos.Pieza;
import Datos.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuardarArchivo {
    // atribbutos de la clase 
    private static CargarArchivo cargador= new CargarArchivo();
    private static ControlNumeroPiezas control=new ControlNumeroPiezas();
    
    public GuardarArchivo(){
        
    }
    // guarda un usuario
    public void guardarUsuario(Usuario usuario){
        // abre el camino para guardarla y pide el objeto a guardar
        File archivoAGuardar= new File("usuario"+usuario.getNombre()+".jp");
        // guara el usuario mandado
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(usuario);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    // guarda una pieza segun su identificador
    public void guardarPieza(Pieza pieza, String identificador){           
            // abre el camino para guardarla y pide el objeto a guardar
            // guarda la pieza
            File archivoAGuardar= new File("pieza"+pieza.getNombre()+identificador+".jp");
            try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
                archivoSalida.writeObject(pieza);
                
            } catch (FileNotFoundException ex) {
                ex.getMessage();
            } catch (IOException ex) {
                ex.getMessage();
            }
    }
    // guarda una pieza existente 
    public void guardarPiezaExistente(Pieza pieza, String nombreArchivo){      
            File archivoAGuardar= new File(nombreArchivo);
            try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
                archivoSalida.writeObject(pieza);
            } catch (FileNotFoundException ex) {
                ex.getMessage();
            } catch (IOException ex) {
                ex.getMessage();
            }
    }
    
    // guarda el contador de piezas
    public void guardarContadorPieza(ControlNumeroPiezas controlPiezas){
        // abre el camino para guardarla y pide el objeto a guardar
            File contadorTarjetas = new File("contadorPiezas.jp");
            try {
                FileOutputStream output = new FileOutputStream(contadorTarjetas);
                ObjectOutputStream objectOutput = new ObjectOutputStream(output);
                objectOutput.writeObject(controlPiezas);
            } catch (IOException ex) {
                ex.printStackTrace();
            }      
    }
    // guarda un mueble 
    public void guardarMueble(Mueble mueble){
        // abre el camino para guardarla y pide el objeto a guardar
        File archivoAGuardar= new File("mueble"+mueble.getNombre()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(mueble);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
        
    }
// guarda la pieza que se le asigno a un mueble    
    public void guardarMueblePiezas(MueblePiezas1 mueble){
        // guarda con el nombre mueblepiezas
        File archivoAGuardar= new File("mueblepiezas"+mueble.getNombre()+mueble.getNombrePieza()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(mueble);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    // guarda el identificador del mueble, el contador
    public void guardarIdentificadorMueble(ControlIdentificadorMueble controlMueble){
        // abre el camino para guardarla y pide el objeto a guardar
            File contadorTarjetas = new File("identificadormueble.jp");
            try {
                FileOutputStream output = new FileOutputStream(contadorTarjetas);
                ObjectOutputStream objectOutput = new ObjectOutputStream(output);
                objectOutput.writeObject(controlMueble);
            } catch (IOException ex) {
                ex.printStackTrace();
            }      
    }
    // guarda un mueble ensamblado 
    public void guardarMuebleEnsamblado(EnsamblarMueble muebleAGuardar){
        File archivoAGuardar= new File("muebleEnsamblado"+muebleAGuardar.getNombre()+muebleAGuardar.getIdentificador()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(muebleAGuardar);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }   
    // guarda un cliente nuevo por medio de su nit
    public void guardarCliente(Cliente cliente){
        // guarda con el nit
        File archivoAGuardar= new File("cliente"+cliente.getNIT()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(cliente);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    // guarda el contador de las facturas
    public void guardarContadorFactura(ControlNumeroFacturas controlFactura){
        // abre el camino para guardarla y pide el objeto a guardar
            File contadorTarjetas = new File("contadorFacturas.jp");
            try {// guarda
                FileOutputStream output = new FileOutputStream(contadorTarjetas);
                ObjectOutputStream objectOutput = new ObjectOutputStream(output);
                objectOutput.writeObject(controlFactura);
            } catch (IOException ex) {

                ex.printStackTrace();
            }      
        
    }
    // guarda una factura
    public void guardarFactura(Factura factura){
        File archivoAGuardar= new File("factura"+factura.getNumeroFactura()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(factura);
            // guarda
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    // guarda una devolucion por el identificador
    public void guardarDevolucion(Devolucion devolucion, int identificadorDevolucion){
        File archivoAGuardar= new File("devolucion"+devolucion.getNombreCliente()+devolucion.getIdentificador()+identificadorDevolucion+devolucion.getNumeroFactura()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(devolucion);// guadrda
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    // guarda el controlador de devolucion
        public void guardarControladorDevoluciones(ControlNumeroDevoluciones controlDevoluciones){
        // abre el camino para guardarla y pide el objeto a guardar
            File contadorTarjetas = new File("contadorDevoluciones.jp");
            try {
                FileOutputStream output = new FileOutputStream(contadorTarjetas);
                ObjectOutputStream objectOutput = new ObjectOutputStream(output);
                objectOutput.writeObject(controlDevoluciones);
            } catch (IOException ex) {
                ex.printStackTrace();
            }      
    }
}
