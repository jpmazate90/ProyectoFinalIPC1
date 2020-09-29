
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
public class Pieza implements Serializable {
    // atributos de la clase
    private String nombre;
    private double precio;
    private boolean estadoPieza;
    private String fechaIngreso;
    private int identificadorUnico;
    private Date fechaEnFormato;
    private SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private SimpleDateFormat formatoSinHora = new SimpleDateFormat("dd/MM/yyyy");
// constructor de la clase
    public Pieza(String nombre, double precio, int identificadorUnico) {
        this.nombre = nombre;
        this.precio = precio;
        this.identificadorUnico=identificadorUnico;
        this.estadoPieza=true;
        
    }
// getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstadoPieza() {
        return estadoPieza;
    }

    public void setEstadoPieza(boolean estadoPieza) {
        this.estadoPieza = estadoPieza;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdentificadorUnico() {
        return identificadorUnico;
    }

    public void setIdentificadorUnico(int identificadorUnico) {
        this.identificadorUnico = identificadorUnico;
    }
    // asigna fecha de llegada
    public void asignarFecha() throws ParseException{
        try {
            this.fechaEnFormato = formato.parse(fechaIngreso);
        } catch (ParseException e) {
            this.fechaEnFormato = formatoSinHora.parse(fechaIngreso);
        }
    }
    
    
    
}
