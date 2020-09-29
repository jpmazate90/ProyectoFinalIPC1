
package Logica;

public class ControladorMuebleVendido {
    // atributos de la clase
    private String nombreMueble;
    private int vecesVendido;
// constructor
    public ControladorMuebleVendido(String nombreMueble) {
        this.nombreMueble = nombreMueble;
        this.vecesVendido = 1;
    }
// getters y setters
    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public int getVecesVendido() {
        return vecesVendido;
    }

    public void setVecesVendido(int vecesVendido) {
        this.vecesVendido = vecesVendido;
    }
    
    
    
}
