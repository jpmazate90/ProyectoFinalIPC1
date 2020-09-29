
package Datos;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */
public class ControlNumeroPiezas implements Serializable{
    
    //atributos privados de la clase
    private int pieza;
    // constructor de la clase controlnumerostarjetas
    public ControlNumeroPiezas(){
        // se asigna 1 al instanciar la clase
        this.pieza=1;
    }
//getters y setters
    public int getPieza() {
        return pieza;
    }

    public void setPieza(int pieza) {
        this.pieza = pieza;
    }
    
    
    
    
    

}
