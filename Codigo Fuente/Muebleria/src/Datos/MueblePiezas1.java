
package Datos;

import java.io.Serializable;
// este fue el que si se utilizo en el proyecto
public class MueblePiezas1 implements Serializable {
    //atributos de la clase
    private String nombre;
    private String nombrePieza;
    private int cantidadPieza;
// constructor 
    public MueblePiezas1(String nombre, String nombrePieza, int cantidadPieza) {
        this.nombre = nombre;
        this.nombrePieza = nombrePieza;
        this.cantidadPieza = cantidadPieza;
    }
//getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public int getCantidadPieza() {
        return cantidadPieza;
    }

    public void setCantidadPieza(int cantidadPieza) {
        this.cantidadPieza = cantidadPieza;
    }
    
    
}
