
package Datos;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */

public class ControlNumeroFacturas implements Serializable {
    //atributo unico
    private int numeroFactura;
    //constructor
    public ControlNumeroFacturas() {
        this.numeroFactura = 1;
    }
//getters y setters
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    
}
