
package Datos;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */
public class Mueble implements Serializable{
    //atributos privados
    private String nombre;
    private double precio;
    private int vecesVendido;
// constructor
    public Mueble(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.vecesVendido=0;
    }
//getters y setters
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

    public int getVecesVendido() {
        return vecesVendido;
    }

    public void setVecesVendido(int vecesVendido) {
        this.vecesVendido = vecesVendido;
    }
    
    
    
}
