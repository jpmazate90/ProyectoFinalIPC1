
package Datos;

import java.io.Serializable;

// controlador de los identificadores de un mueble
public class ControlIdentificadorMueble implements Serializable{
    //atributo unico
    private int numeroMueble;
    //constructor
    public ControlIdentificadorMueble(){
        this.numeroMueble=1;
    }
    //getters y setters

    public int getNumeroMueble() {
        return numeroMueble;
    }

    public void setNumeroMueble(int numeroMueble) {
        this.numeroMueble = numeroMueble;
    }
    
    
}
