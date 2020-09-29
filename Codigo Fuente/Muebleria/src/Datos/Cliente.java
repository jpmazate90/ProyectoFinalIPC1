
package Datos;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */
public class Cliente implements Serializable{
    // atributos de un cliente
    private int NIT;
    private String nombre;
    private String direccion;
    // constructor
    public Cliente(int NIT, String nombre, String direccion) {
        this.NIT = NIT;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    //getters y setters
    public int getNIT() {
        return NIT;
    }

    public void setNIT(int NIT) {
        this.NIT = NIT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
