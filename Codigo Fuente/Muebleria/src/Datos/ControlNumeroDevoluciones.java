
package Datos;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */
public class ControlNumeroDevoluciones implements Serializable {
    //atributo unico
    private int numeroDevolucion;
    //constructor
    public ControlNumeroDevoluciones(){
        this.numeroDevolucion=1;
    }
//getters y setters
    public int getNumeroDevolucion() {
        return numeroDevolucion;
    }

    public void setNumeroDevolucion(int numeroDevolucion) {
        this.numeroDevolucion = numeroDevolucion;
    }
    
    
}
