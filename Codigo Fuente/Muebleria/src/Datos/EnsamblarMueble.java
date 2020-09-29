
package Datos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpmazate
 */

public class EnsamblarMueble implements Serializable{
    //atributos de la clase
    private String nombre;
    private String usuario;
    private String fecha;
    private String identificador;
    private double costo;
    private double precioVenta;
    private boolean estadoMueble;
    private Date fechaEnFormato;
    private SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
    
//constructor
    public EnsamblarMueble(String nombre, String usuario, String fecha, String identificador, double costo, double precioVenta) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.fecha = fecha;
        this.identificador = identificador;
        this.costo = costo;
        this.precioVenta = precioVenta;
        this.estadoMueble = true;
        //asigna la fecha 
        try {
            asignarFecha();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    

    
    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isEstadoMueble() {
        return estadoMueble;
    }

    public Date getFechaEnFormato() {
        return fechaEnFormato;
    }

    public void setFechaEnFormato(Date fechaEnFormato) {
        this.fechaEnFormato = fechaEnFormato;
    }
    

    public void setEstadoMueble(boolean estadoMueble) {
        this.estadoMueble = estadoMueble;
    }
    //asigna fecha
    public void asignarFecha() throws ParseException{
        try {
            this.fechaEnFormato = formato.parse(fecha);
        } catch (ParseException e) {
            this.fechaEnFormato = formatoSinHora.parse(fecha);
        }
    }
    
    
    
    
    
    
    
}
